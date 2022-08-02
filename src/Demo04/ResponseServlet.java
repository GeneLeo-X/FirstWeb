package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@WebServlet("/response")
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过响应对象设置响应行的内容
        //状态码
        resp.setStatus(400);//主动设置状态码
        //设置响应头
        //允许重名的key存在（key不建议重复，保证 唯一性）
        resp.addHeader("name","lixiuchun");
        resp.addIntHeader("age",22);
        resp.addDateHeader("birthday",new Date().getTime());
        resp.addHeader("name","hanhan");


        System.out.println("--------------------");

        //set方式设置头相当于仅允许有一个同名key存在。
        resp.setHeader("name","Lili");
        //设置重定向
        //resp.setStatus(302);//重定向的的状态码,会改变地址栏请求信息（URL）
        //resp.setHeader("Location","/quickServlet");

        //重定向的封装方法
        //resp.sendRedirect("/Test.html");//应用上下文路径，Application Context - 若为/就不需要写。但是上线项目或者新IDEA版本自带路径的话就需要添加

        System.out.println("-----------向响应体中输出内容--------------");


//        resp.setCharacterEncoding("utf-8");//简单了解 -- 不使用（被淘汰）
        resp.setContentType("text/html;charset=utf-8");//推荐使用的 兼容了setCharacterEncoding()
        resp.getWriter().write("测试响应回浏览器的数据...");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
