package Demo02;

import Demo02.bean.Product;
import Demo02.pool.C3p0Pool;
import Demo02.pool.DbcpPool;
import Demo02.pool.MyConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Example03 {
    public static void main(String[] args) {


        System.out.println(getProductList());//获取商品列表
        System.out.println("----------------------------------");

        System.out.println(deleteProductById(6L));//删除指定商品
        System.out.println("-----------------------------------");

        Product product = new Product();
        product.setPrice(new BigDecimal(123));
        product.setPname("精品黄瓜");
        product.setCid(new Long(4L));

        addProduct(product);//添加商品
        System.out.println("------------------------------------");
        System.out.println(getProductByPid(7L));


    }

    /**
     * 获取商品列表
     * @return
     */
    public static List<Product> getProductList(){
        List<Product> productList = new ArrayList<>();
        try {

            MyConnectionPool pool = new MyConnectionPool();//创建连接池对象
            //从池子中获取连接对象
            Connection conn = pool.getConnection();//创建连接池
            //给列起别名 as可以省略，结果集中的名称也会变
            String sql = "select p.pid,p.pname,p.cid,p.price , p.create_time as createTime from product p;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Product product = new Product();


                product.setCid(rs.getLong("cid"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setPid(rs.getLong("pid"));
                product.setCreateTime(rs.getString("createTime"));

                productList.add(product);

            }

            //向池子中归还连接对象
            pool.releaseConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;

    }

    /**
     * 删除商品
     * @param pid
     * @return
     */
    public static Integer deleteProductById(long pid){
        Connection conn = C3p0Pool.getConnection();
        int row;
        try {
            PreparedStatement pstmt = conn.prepareStatement("delete from product where pid = ?");
            pstmt.setLong(1,pid);
            row = pstmt.executeUpdate();
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    public static Integer addProduct(Product product){
        int rows;
        try {
            Connection conn = C3p0Pool.getConnection();
            //在添加时间时可以在sql语句中使用now()方法调出当前时间
            String sql = "insert into product(pname , price , cid)value (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,product.getPname());
            pstmt.setBigDecimal(2,product.getPrice());
            pstmt.setLong(3,product.getCid());
            rows = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;

    }

    /**
     * 使用Dbcp连接池对数据库进行查询操作，根据商品的pid查询并返回product对象
     * @param pid
     * @return
     */
    public static Product getProductByPid(Long pid){
        Product product = null;
        try {
            Connection conn = DbcpPool.getConnection();
            String sql = "select p.pid , p.pname , p.price , p.cid" + ", p.create_time as createTime from product p where pid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,pid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new Product();
                product.setCid(rs.getLong("cid"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCreateTime(rs.getString("createTime"));
                product.setPid(pid);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }


}
