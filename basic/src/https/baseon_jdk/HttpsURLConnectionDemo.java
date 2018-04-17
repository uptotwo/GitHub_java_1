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
 * 使用JDK 的HttpsURLConnection发送https消息的对接示例代码
 * 
 * */
public class HttpsURLConnectionDemo
{
    //示例代码 实际的证书路径、证书密钥请根据实际情况替换
    public static String SELFCERTPATH = "G:/certificate/outgoing.CertwithKey.pkcs12";
    
    public static String SELFCERTPWD = "IoM@1234";
    
    public static String TRUSTCAPATH = "G:/certificate/ca.jks";
    
    //这里的密码不是CA证书的密码，而是jks证书仓库的密码 （CA证书本身不包含私钥，因此也没有密码）  
    public static String TRUSTCAPWD = "Huawei@123";
    
    /**
     * 双向认证场景 Two-Way Authentication
     * 双向认证场景下，客户端需要
     *   1、导入自己证书，提供自己证书供服务端校验   
     *   2、导入服务器CA证书，使用服务端CA证书校验服务端发送过来的证书   
     *   3、设置不校验域名  （非商用环境下，沒有使用域名访问）
     *   */
    public static void initSSLConfigForTwoWay() throws Exception
    {
        //1、导入自己证书
        KeyStore selfCert = KeyStore.getInstance("pkcs12");
        selfCert.load(new FileInputStream(SELFCERTPATH), SELFCERTPWD.toCharArray());
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
        kmf.init(selfCert, SELFCERTPWD.toCharArray());
        
        //2、导入服务器CA证书
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        
        SSLSocketFactory ssf = sc.getSocketFactory(); 
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
        //3、关闭证书域名校验  
        //(联调测试环境中，一般没有申请域名，而是使用ip进行访问的，这种场景下必须关闭证书的域名校验功能)
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
     * 单向认证场景 One-way authentication
     * 单向认证场景下，客户端需要
     *   1、导入服务器CA证书，使用服务端CA证书校验服务端发送过来的证书   
     *   2、设置不校验域名  （非商用环境下，沒有使用域名访问）
     *   */
    public static void initSSLConfigForOneWay() throws Exception
    {
        //1、导入服务器CA证书
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        
        SSLSocketFactory ssf = sc.getSocketFactory(); 
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
        //2、关闭证书域名校验  
        //(联调测试环境中，一般没有申请域名，而是使用ip进行访问的，这种场景下必须关闭证书的域名校验功能)
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
        //示例1：双向认证
        try
        {
//            URL myURL1 = new URL("https://185.1.8.218:8743/iocm/app/sec/v1.1.0/login");
            URL myURL1 = new URL("https://127.0.0.1:8543/SmartStreet/"); 
            
            
            
            
            //必须在创建HttpsURLConnection之前初始化配置！
            initSSLConfigForTwoWay();
            HttpsURLConnection httpsConn1 = (HttpsURLConnection)myURL1.openConnection();
            
            // 取得该连接的输入流，以读取响应内容
            InputStreamReader insr1 = new InputStreamReader(httpsConn1.getInputStream());

            // 读取服务器的响应内容并显示
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
        
        
        //示例2：单向认证 
//        try
//        {
//            URL myURL2 = new URL("https://192.168.0.107:8643");
//            
//            //必须在创建HttpsURLConnection之前初始化配置！
//            initSSLConfigForOneWay();
//            HttpsURLConnection httpsConn2 = (HttpsURLConnection)myURL2.openConnection();
//            // 取得该连接的输入流，以读取响应内容
//            InputStreamReader insr2 = new InputStreamReader(httpsConn2.getInputStream());
//    
//            // 读取服务器的响应内容并显示
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
