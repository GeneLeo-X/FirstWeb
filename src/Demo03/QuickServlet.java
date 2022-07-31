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
 */

public class QuickServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(" init method called...");
    }

    @Override
    public ServletConfig getServletConfig() {
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
