package https.baseon_jdk;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;


/**
 * ʹ��JDK ��HttpsURLConnection����https��Ϣ�ĶԽ�ʾ������
 * 
 * */
public class HttpsURLConnectionDemo
{
    //ʾ������ ʵ�ʵ�֤��·����֤����Կ�����ʵ������滻
    public static String SELFCERTPATH = "G:/certificate/outgoing.CertwithKey.pkcs12";
    
    public static String SELFCERTPWD = "IoM@1234";
    
    public static String TRUSTCAPATH = "G:/certificate/ca.jks";
    
    //��������벻��CA֤������룬����jks֤��ֿ������ ��CA֤�鱾������˽Կ�����Ҳû�����룩  
    public static String TRUSTCAPWD = "Huawei@123";
    
    /**
     * ˫����֤���� Two-Way Authentication
     * ˫����֤�����£��ͻ�����Ҫ
     *   1�������Լ�֤�飬�ṩ�Լ�֤�鹩�����У��   
     *   2�����������CA֤�飬ʹ�÷����CA֤��У�����˷��͹�����֤��   
     *   3�����ò�У������  �������û����£��]��ʹ���������ʣ�
     *   */
    public static void initSSLConfigForTwoWay() throws Exception
    {
        //1�������Լ�֤��
        KeyStore selfCert = KeyStore.getInstance("pkcs12");
        selfCert.load(new FileInputStream(SELFCERTPATH), SELFCERTPWD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(selfCert, SELFCERTPWD.toCharArray());
        
        //2�����������CA֤��
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        
        SSLSocketFactory ssf = sc.getSocketFactory(); 
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
        //3���ر�֤������У��  
        //(�������Ի����У�һ��û����������������ʹ��ip���з��ʵģ����ֳ����±���ر�֤�������У�鹦��)
        HostnameVerifier allHostsValid = new HostnameVerifier() 
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                // TODO Auto-generated method stub
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
    
    /**
     * ������֤���� One-way authentication
     * ������֤�����£��ͻ�����Ҫ
     *   1�����������CA֤�飬ʹ�÷����CA֤��У�����˷��͹�����֤��   
     *   2�����ò�У������  �������û����£��]��ʹ���������ʣ�
     *   */
    public static void initSSLConfigForOneWay() throws Exception
    {
        //1�����������CA֤��
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        
        SSLSocketFactory ssf = sc.getSocketFactory(); 
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
        //2���ر�֤������У��  
        //(�������Ի����У�һ��û����������������ʹ��ip���з��ʵģ����ֳ����±���ر�֤�������У�鹦��)
        HostnameVerifier allHostsValid = new HostnameVerifier() 
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                // TODO Auto-generated method stub
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
    
    
    
    
    public static void main(String args[]) 
    {   
        //ʾ��1��˫����֤
        try
        {
//            URL myURL1 = new URL("https://185.1.8.218:8743/iocm/app/sec/v1.1.0/login");
            URL myURL1 = new URL("https://127.0.0.1:8543/SmartStreet/"); 
            
            
            
            
            //�����ڴ���HttpsURLConnection֮ǰ��ʼ�����ã�
            initSSLConfigForTwoWay();
            HttpsURLConnection httpsConn1 = (HttpsURLConnection)myURL1.openConnection();
            
            // ȡ�ø����ӵ����������Զ�ȡ��Ӧ����
            InputStreamReader insr1 = new InputStreamReader(httpsConn1.getInputStream());

            // ��ȡ����������Ӧ���ݲ���ʾ
            int respInt1 = insr1.read();
            while (respInt1 != -1) 
            {
                System.out.print((char) respInt1);
                respInt1 = insr1.read();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        //ʾ��2��������֤ 
//        try
//        {
//            URL myURL2 = new URL("https://192.168.0.107:8643");
//            
//            //�����ڴ���HttpsURLConnection֮ǰ��ʼ�����ã�
//            initSSLConfigForOneWay();
//            HttpsURLConnection httpsConn2 = (HttpsURLConnection)myURL2.openConnection();
//            // ȡ�ø����ӵ����������Զ�ȡ��Ӧ����
//            InputStreamReader insr2 = new InputStreamReader(httpsConn2.getInputStream());
//    
//            // ��ȡ����������Ӧ���ݲ���ʾ
//            int respInt2 = insr2.read();
//            while (respInt2 != -1) 
//            {
//                System.out.print((char) respInt2);
//                respInt2 = insr2.read();
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}
