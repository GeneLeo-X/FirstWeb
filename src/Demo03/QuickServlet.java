package Demo03;

import javax.servlet.*;
import java.io.IOException;

/**
 * 导入Servlet方法（两种）
 * 1.下载Servlet的jar包并导入
 * 2.直接使用Tomcat里面内置的Servlet与jsp包
 *
 * 常用方法介绍：
 * init -- 初始化方法， 信息、数据 - 第一次访问调用
 * service -- 业务方法，每次访问必会调用
 * destroy --   销亡方法，销毁的时候调用
 * getServletConfig -- 获取Servlet配置对象的，需要获取Servlet相关信息的时候可以使用
 *
 * Servlet生命周期：（面试题）
 * 1.Servlet何时创建：
 *  默认第一次访问的时候创建该对象，由tomcat引擎创建
 * 2.Servlet何时销毁：
 * 服务器关闭Servlet就销毁了
 * 3.每次访问必然执行的方法：
 * service(ServletRequest req,ServletResponse res)方法
 *
 *
 * ServletContext对象
 *  1、可以获取配置的全局参数
 *  2、获取某个文件的绝对路径
 *  3、它是一个域对象，范围最大的域对象
 *
 */

public class QuickServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {//Servlet创建的时候执行
        System.out.println(" init method called...");

        //一个web应用只用一个servletContext对象
        ServletContext servletContext = servletConfig.getServletContext();
        //servletContext对象获取资源的绝对路径
        System.out.println("绝对路径：" + servletContext.getRealPath("img/picture01.jpg"));
    }

    @Override
    public ServletConfig getServletConfig() {//获得该Servlet的配置信息
        System.out.println(" getServletConfig method called...");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println(" service method called...");
    }

    @Override
    public String getServletInfo() {
        System.out.println(" getServletInfo method called...");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println(" destroy method called...");
    }
}
