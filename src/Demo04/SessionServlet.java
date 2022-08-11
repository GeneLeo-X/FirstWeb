package Demo04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //若存在session则获取使用，否则创建一个新的session
        //ServletContext > session也是域对象 > request

        HttpSession session = req.getSession();

        String JSEESIONID = session.getId();

        System.out.println("JSEESIONID: " + JSEESIONID);


        //Session中可以存中文
        session.setAttribute("name","张三");//使用session进行存值操作


        //session会话基于cookie会话实现JSEESIONID为session的唯一标识，但需要通过cookie技术进行存储实现session
        //当JSEESIONID消失session会话也就结束了
        //要想延长session的会话时长，就需要对JSEESIONID进行cookie有效时间的特殊处理
        Cookie cookie = new Cookie("JSEESIONID",JSEESIONID);
        cookie.setMaxAge(60*60);//one hour 与session时效保持一致
        resp.addCookie(cookie);


        /**
         * Session对象的生命周期（面试、笔试题）
         * 创建：
         *      第一次执行request.getSession()时创建
         *     销毁：
         *      1）服务器（非正常）关闭时
         *      2）session过期、失效时（默认30min）
         *     问题：
         *      时间的起算点：从何时开始计算30min?
         *          从不操作服务器的资源开始计时
         *
         *          可以在web。xml中进行配置
         *          <session-config>
         *             <session-timeout>30</session-timeout>
         *          <session-config/>
         *
         *      3)手动销毁session
         *          session。invalidate();
         *
         *      session的作用范围：
         *          默认在一次会话中，也就是在，一次会话中任何资源公用一个session对象
         *
         *      面试题：
         *          浏览器关闭,session就销毁了?
         *              不对
         *
         *
         *
         */



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
