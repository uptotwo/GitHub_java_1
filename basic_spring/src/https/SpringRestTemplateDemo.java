package https;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 使用Spring 的RestTemplate发送https消息的对接示例代码
 * 
 * */
public class SpringRestTemplateDemo
{
  //示例代码 实际的证书路径、证书密钥请根据实际情况替换
    public static String SELFCERTPATH = "D://outgoing.CertwithKey.pkcs12";
    
    public static String SELFCERTPWD = "IoM@1234";
    
    public static String TRUSTCAPATH = "D://ca.jks";
    
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
    
    
    public static void main(String args[]) throws Exception, RestClientException
    {
        
        //示例1：双向认证
        try 
        {
            initSSLConfigForTwoWay();
            RestTemplate resttemplate1 = new RestTemplate();
            ResponseParams rsp1 = resttemplate1.getForObject(
                    "https://185.1.8.218:8743/iocm/app/sec/v1.1.0/login", ResponseParams.class);
            System.out.println(rsp1.getParam1()); 
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //示例2：单向认证
        try 
        {
            initSSLConfigForOneWay();
            RestTemplate resttemplate2 = new RestTemplate();
            ResponseParams rsp2 = resttemplate2.getForObject(
                    "https://185.1.8.218:8643", ResponseParams.class);
            System.out.println(rsp2.getParam1());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

class ResponseParams
{
    private String param1;
    
    private String param2;

    public String getParam1()
    {
        return param1;
    }

    public void setParam1(String param1)
    {
        this.param1 = param1;
    }

    public String getParam2()
    {
        return param2;
    }

    public void setParam2(String param2)
    {
        this.param2 = param2;
    }
}