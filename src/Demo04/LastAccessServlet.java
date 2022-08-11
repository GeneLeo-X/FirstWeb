package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/lastAccess")
public class LastAccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Cookie里面存储不建议中文，空格
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String lastAccessTime = df.format(new Date());
        //使用请求头获取Cookie信息
        Cookie[] cookies = req.getCookies();
        String time = null;
        if(null != cookies){
            for(Cookie c:cookies){
                if(c.getName().equals("LassAccessTime")){
                    time = c.getValue();
                }
            }
        }


        resp.setContentType("text/html;charset=utf-8");//推荐使用的 兼容了setCharacterEncoding()

        if(time == null){
            resp.getWriter().write("欢迎您，您是第一次访问我们的网站...");
        }else{

            resp.getWriter().write("您上一次访问的时间为：" + time);
        }

        Cookie cookie = new Cookie("LassAccessTime" , lastAccessTime);
        cookie.setMaxAge(60*60*24*7);//one week
        //cookie.setPath("/lastAccess")
        resp.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
