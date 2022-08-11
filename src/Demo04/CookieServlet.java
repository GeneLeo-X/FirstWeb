package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //实例化一个cookie对象
        Cookie cookie= new Cookie("name" , "tom");
        //为cookie设置时效
        //删除客户端的cookie：
        /**
         * 如果想删除客户端的已经存储的cookie信息，那么就使用同名同路径的持久化时间为0的cookie进行覆盖即可
         */
        cookie.setMaxAge(60*10);//10min
        //设定某个指定路径下才会携带cookie信息
        cookie.setPath("/cookie");//只有cookie路径下才会携带cookie信息
        //resp 将一个cookie设定于浏览器中
        //resp.addCookie(cookie);

        /**
         * 服务端怎么接收客户端携带的Cookie
         * cookie信息是以请求头的方式发送到服务器端的：
         * 可以通过request获得所有的Cookie：
         *      Cookie[] cookies = request.getCookies();
         */

        Cookie c1 = new Cookie("age","33");
        resp.addCookie(c1);//向客户端存储cookie信息
        Cookie c2 = new Cookie("test","test-val");
        resp.addCookie(c2);



    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
