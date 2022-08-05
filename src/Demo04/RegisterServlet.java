package Demo04;

import Demo02.pool.C3p0Pool;
import Demo04.bean.User;
import com.mchange.v2.beans.BeansUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理前端发过来的请求数据的中文乱码问题
        req.setCharacterEncoding("utf-8");
//        String username = req.getParameter("username");
//        System.out.println("username = " + username);


        Map<String,String[]> parameterMap = req.getParameterMap();//获取注册页面的请求信息
        User user = new User();
        try {
            //将前端传递过来的表单数组，自动映射到JavaBean对象里
            BeanUtils.populate(user,parameterMap);//将请求信息与user对象的信息一一映射
            //数据库中若某列是存放多个值的话，   （xxx , xxx）以逗号分隔的
            String[] hobbies = parameterMap.get("hobby");
            System.out.println("电话号码："+ parameterMap.get("phone"));

            String hobby = Arrays.toString(hobbies);
            hobby = hobby.substring(1 , hobby.length() - 1);
            //自动映射之后，不满足的属性设定，可以单独再次处理
            user.setHobby(hobby);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        Integer row = register(user);//将user对象的信息存入数据库中返回影响的行数
        if(row > 0){
            System.out.println("注册成功！！！");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 将user对象的信息存入数据库中返回影响的行数
     * @param user register.html注册的用户信息对象
     * @return  在数据库表中新数据对表影响的行数
     */
    public Integer register(User user){

        try {
            QueryRunner qr = new QueryRunner(C3p0Pool.getDataSource());
            System.out.println("----------------------");
            System.out.println("Name:" + user.getName());
            System.out.println("UserName:" + user.getUsername());
            System.out.println("PassWord:" + user.getPassword());
            System.out.println("Sex:" + user.getSex());
            System.out.println("PhoneNumber:" + user.getPhone());
            System.out.println("hobby:" + user.getHobby());
            System.out.println("-----------------------");







            int rows = qr.update("insert into user values(null , ? , ? ,? ,? ,? ,?)"
                    , user.getName() , user.getUsername() , user.getPassword() , user.getSex() , user.getPhone() , user.getHobby());
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}

