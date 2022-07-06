package Demo02;

import Demo02.bean.Product;
import Demo02.pool.C3p0Pool;
import Demo02.pool.MyConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Example03 {
    public static void main(String[] args) {
        System.out.println(getProductList());
        System.out.println(deleteProductById(6));

    }

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


}
