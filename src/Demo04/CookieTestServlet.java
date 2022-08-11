package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookieTest")
public class CookieTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        /**
         * 服务端怎么接收客户端携带的Cookie
         * cookie信息是以请求头的方式发送到服务器端的：
         * 可以通过request获得所有的Cookie：
         *      Cookie[] cookies = request.getCookies();
         */
        //使用请求对象取请求头获取cookie信息
        Cookie[] cookies = req.getCookies();
        if(null != cookies){
            for(Cookie c:cookies){

                //浏览器获取服务器设定的cookie值
                if(c.getName().equals("test")){
                    System.out.println(c.getValue());;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
