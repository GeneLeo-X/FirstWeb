package Demo02;

import Demo02.bean.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Example01 {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setAge(30);
        stu.setSid(20200003);
        stu.setSno("10002");
        stu.setSname("张飞");
        System.out.println(updateStudent(stu));

    }

    public static Integer updateStudent(Student stu) {
        Connection conn = null;
        Statement stmt = null;
        int row = 0;//未更新成功，返回0
        try {
            conn = JdbcUtil.getConnection();
            stmt = conn.createStatement();
            //Statement 对象如果要进行变量使用的话，只能进行字符串拼接
            String sql = "update student set sno = '"+stu.getSno()+"',sname ='"+stu.getSname()+"',age = '"+stu.getAge()+"' where sid ="+stu.getSid() ;
            row = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //JdbcUtil.releaseRes(conn, stmt, null);//因为是更新所以没有结果集，所以不需要释放结果集
        }
        return row;
    }
}
