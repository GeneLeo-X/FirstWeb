package Demo04;

import Demo02.pool.C3p0Pool;
import Demo04.bean.User;
import com.mysql.cj.Session;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止中文乱码
        req.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        System.out.println(username);
//        System.out.println(password);
        User user = Login(username,password);
        if(null != user){
            HttpSession session =req.getSession();
            session.setAttribute("user",user);//使用session存入user对象信息


            resp.sendRedirect(req.getContextPath() + "/index.jsp");//登录成功，跳转到首页
        }

        else
        {
            System.out.println("登录失败，请重新登录！！！");
            req.setAttribute("msg","用户名或密码错误，请重新登录！！");
            req.getRequestDispatcher("/login.html").forward(req,resp);//重新跳回登录页面

        }
    }

    private User Login(String username, String password) {
        try {
            QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
            String sql = "select u.uid , u.name from user u where u.username = ? and u.password = ?";
            User user = qr.query(sql,new BeanHandler<>(User.class),username,password);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
