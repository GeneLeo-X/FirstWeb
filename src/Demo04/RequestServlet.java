package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设定获取处理乱码问题，通常是在方法进入的时机处理
        req.setCharacterEncoding("utf-8");
        //获取请求方式,浏览器地址栏直接访问所有的请求都是GET方式
        String method = req.getMethod();
        System.out.println("Method: " + method);

        //获取请求资源路径
        String uri = req.getRequestURI();
        System.out.println("uri路由："+ uri);
        StringBuffer url = req.getRequestURL();
        System.out.println("url地址：" + url);

        System.out.println("应用的上下文路径:"+ req.getContextPath());

        //当进行重定向时(最好)，防止出现应用上下文路径写死
        //resp.sendRedirect(req.getContextPath() + "/text.html");


        //get方式的获取参数
        //可以获取到？后面的所有参数，但多个参数的时候需要使用&进行参数分隔
        System.out.println("----------get方式的获取参数------------");
//        String queryString = req.getQueryString();//简单了解即可

//        queryString = new String(queryString.getBytes("iso8859-1"),("UTF-8"));//get请求方式时对中文乱码的处理
            //或者queryString = URLDecoder.decode(queryString,"utf-8");//不关心本来的字符集，直接转换编码格式
//
//        System.out.println("queryString = " + queryString);
//        String [] split = queryString.split("&");
//        System.out.println(Arrays.toString(split));

        //获取客户端的IP地址
        System.out.println("客户端的IP地址 " + req.getRemoteAddr());

        //获取请求头信息
        System.out.println("---------获取请求头信息--------");
        Enumeration<String> headerNames = req.getHeaderNames();

        while(headerNames.hasMoreElements())//打印所有请求头信息
            System.out.println(headerNames.nextElement());

        //获取单个请求头
        System.out.println("----------获取单个请求头-----------");
        String header = req.getHeader("user-agent");
        System.out.println(header);

        System.out.println("---------------防盗链referer-----");
        //referer：必须有来源才会存在的头



        System.out.println("-----------------获取请求体参数（无论post、get都可以）------");
        //测试时URL：
        //htttp://localhost:8081/request?username=zhangsan&password=123&hobby=football&hobby=basketball
        System.out.println("用户名：" + req.getParameter("username"));//获取单个响应头信息
        //某个key的值的中文乱码问题，使用setCharacterEncoding方式没有处理掉的情况下，使用以下方式单独处理
//        String username = req.getParameter("username");
//        username = new String(username.getBytes("iso8859-1"),"utf-8");






        System.out.println("爱好：" + Arrays.toString(req.getParameterValues("hobby")));//获取同名的响应头信息的String数组


        System.out.println("-----------获得请求头信息的关键词-----------");
//        Enumeration enumeration = getInitParameterNames();
//        while(enumeration.hasMoreElements())
//            System.out.println(enumeration.nextElement());


        System.out.println("-----------map形式获取请求参数---------");//推荐你使用
        Map<String,String[]> parameterMap = req.getParameterMap();
        parameterMap.forEach((k,v)->{
            System.out.println(k + "->" + Arrays.toString(v));
        });

        //ServletContext >request也是一种域对象 范围是一次请求转发中
        //getRequestDispatcher ：存放请求路径 - 相当是将处理交由其他页面或者Servlet类
        //将需要其他页面或者servlet处理的内容，存储到request域中
        req.setAttribute("name","李四");
        req.getRequestDispatcher("/requestTest").forward(req,resp);//forward 才会执行真正的跳转



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

}
