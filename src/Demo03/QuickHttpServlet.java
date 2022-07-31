package Demo03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* HttpServlet : Servlet的子类。方便我们使用Servlet不需写一些无用的方法
 *
 * @WebServlet : 标记一个类为Servlet类，相当于 web.xml中配置了<servlet></><servlet-mapping><servlet-mapping/>
 * 里面配置的name = "quickHttp",可以省略不写
 * 多个属性之间使用，隔开。并且要求属性名必须写上。若为一个属性名时可以省略不写。
 *
 * 浏览器直接访问路径，就是默认的get方式
 *
 */





@WebServlet(urlPatterns = "/quickHttpServlet")
public class QuickHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("使用了doGet方法");//底层其实还是使用的Service方法
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("使用了doPost方法");//底层其实还是使用的Service方法
        System.out.println("统一业务代码出口");
    }
}
