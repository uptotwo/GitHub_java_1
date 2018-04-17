package servletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/*
 *  ServletContextListener监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期
 *  
 *  在其初始化和销毁的时候执行所指定的事件
 */
public class MyListener implements ServletContextListener{
	
	
/*
 * (non-Javadoc)
 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
 * 当Servlet 容器（eg.Tomcat）启动Web应用时调用该方法。在调用完该方法之后，
 * 容器再对Filter 初始化，
 * 并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("contextDestroyed");
		System.out.println();
		System.out.println();
		System.out.println();
	}
/*
 * (non-Javadoc) 
 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
 *  contextDestroyed(ServletContextEvent sce) ：当Servlet 容器终止Web 应用时调用该方法。
 *  在调用该方法之前，
 *  容器会先销毁所有的Servlet 和Filter 过滤器。
 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("contextInitialized");
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
}
