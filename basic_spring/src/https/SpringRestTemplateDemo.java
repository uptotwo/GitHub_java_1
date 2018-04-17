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
 * ʹ��Spring ��RestTemplate����https��Ϣ�ĶԽ�ʾ������
 * 
 * */
public class SpringRestTemplateDemo
{
  //ʾ������ ʵ�ʵ�֤��·����֤����Կ�����ʵ������滻
    public static String SELFCERTPATH = "D://outgoing.CertwithKey.pkcs12";
    
    public static String SELFCERTPWD = "IoM@1234";
    
    public static String TRUSTCAPATH = "D://ca.jks";
    
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
    
    
    public static void main(String args[]) throws Exception, RestClientException
    {
        
        //ʾ��1��˫����֤
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
        
        //ʾ��2��������֤
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