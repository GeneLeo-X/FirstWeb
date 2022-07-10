package Demo02;

import Demo02.bean.Order;
import Demo02.pool.C3p0Pool;
import com.mysql.cj.xdevapi.Collection;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Example04 {
    public static void main(String[] args) {
        System.out.println(getOrderList());//获得订单表信息
        System.out.println("------------------------");
        System.out.println("订单总数为："+ getTotalOrders());//获得订单数量
        System.out.println("------------------------");
        System.out.println(getOrderByOno("123457"));//根据订单表中的订单号ono进行订单信息的获取

    }


    /**
     * 获得订单表的信息
     * @return
     */
    public static List<Order> getOrderList(){
        //默认要给该对象设置数据源 -  让其关联某个数据库
        List<Order> orderList = Collections.EMPTY_LIST;
        try {
            QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
            //名为order 的数据表是mysql自带的，创建表时要与此区分，否则出现数据为空的异常
            String sql = "select o.oid , o.ono , o.create_time , o.total_price from t_order o";
//            String sql ="select * from t_order";
            orderList = qr.query(sql,new BeanListHandler<>(Order.class));
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    /**
     * 获得订单 的数量
     * @return
     */
    public static Long getTotalOrders(){
        Long count = 0L;
        try {
            QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
            //count :函数，里面使用* -- 推荐的，oid 主键， 1（用的时候 要保证列的值不能为空）
            String sql = "select count(*) from t_order";
            count = (Long)qr.query(sql, new ScalarHandler());
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据订单表中订单的订单号获取该订单的信息
     * @param ono
     * @return
     */
    public static Order getOrderByOno(String ono){


        try {
            QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
            String sql = "select o.oid , o.ono , o.create_time createTime , o.total_price as totalPrice from t_order o where o.ono = ?";
            Order order  =  qr.query(sql,new BeanHandler<>(Order.class),ono);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
