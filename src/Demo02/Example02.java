package Demo02;

import Demo02.bean.User;

import java.sql.*;

public class Example02 {

    public static void main(String[] args) {
        /*
            SQL片段的方式进行SQL攻击
            eg：
                userName = "李展 'or' 张珊";
                去传入数据库中会发生查找张珊的情况
            如何防止SQL攻击
                使用PreparedStatement （预编译声明、Statement 的子接口）代替Statement
         */
//        User user = login("李展","123");
        User user = loginForPreparedStmt("李展","123");
        if(user!=null){
            System.out.println(user.getName()+"已经成功登录");
        }
        else{
            System.out.println("用户名或密码错误");
        }

    }

    /**
     * PreparedStatement应用
     * 1、防止SQL攻击
     * 2、预编译SQL语句
     * 3、提高代码的可读性，以及可维护性
     * 4、提高效率（重复使用同一模板，给予   其不同的参数来）
     * @param userName 用户名
     * @param passWord 登录密码
     * @return  用户对象
     */
    public static User loginForPreparedStmt(String userName, String passWord){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = JdbcUtil.getConnection();
            //使用? 去占位
            String sql = "select u.uid,u.name from user u where u.username = ? and u.password = ?";
            //SQL预加载
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2,passWord);
            rs = pstmt.executeQuery();
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                //user.setUserName(rs.getString("username"));
                user.setUid(rs.getInt("uid"));
                //user.setPassWord(rs.getString("password"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            JdbcUtil.releaseRes(conn,pstmt,rs);
        }
        return user;
    }


    public static User login(String userName, String passWord){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.createStatement();
            //给表起别名  表名 别名
            String sql = "select u.uid,u.name from user u where u.username='"+userName+"' and password = '"+passWord+"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                user = new User();
                user.setName(rs.getString("name"));
                //user.setUserName(rs.getString("username"));
                user.setUid(rs.getInt("uid"));
                //user.setPassWord(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.releaseRes(conn,stmt,rs);
        }
        return user;

    }

}
