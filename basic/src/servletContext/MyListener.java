package servletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/*
 *  ServletContextListener���� ServletContext ������������ڣ�ʵ���Ͼ��Ǽ��� Web Ӧ�õ���������
 *  
 *  �����ʼ�������ٵ�ʱ��ִ����ָ�����¼�
 */
public class MyListener implements ServletContextListener{
	
	
/*
 * (non-Javadoc)
 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
 * ��Servlet ������eg.Tomcat������WebӦ��ʱ���ø÷������ڵ�����÷���֮��
 * �����ٶ�Filter ��ʼ����
 * ���Ҷ���Щ��Web Ӧ������ʱ����Ҫ����ʼ����Servlet ���г�ʼ����
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
 *  contextDestroyed(ServletContextEvent sce) ����Servlet ������ֹWeb Ӧ��ʱ���ø÷�����
 *  �ڵ��ø÷���֮ǰ��
 *  ���������������е�Servlet ��Filter ��������
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
