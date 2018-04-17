package https.baseon_Apache;

import java.io.*;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * ʹ��apcehe ��httpclient����https��Ϣ�ĶԽ�ʾ������
 * 
 * ʾ��������ʹ�õ���httpclient4.2.6�汾��DefaultHttpClient
 * �����httpclient4.3���ϰ汾 ����ʹ��CloseableHttpClient�滻
 * */
public class HttpsClientDemo extends DefaultHttpClient
{
    //ʾ������  ֤��·����֤����Կ�����ʵ������滻
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
    public void initSSLConfigForTwoWay() throws Exception
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
        
        //3���ر�֤������У��  
        //(�������Ի����У�һ��û����������������ʹ��ip���з��ʵģ����ֳ����±���ر�֤�������У�鹦��)
        SSLSocketFactory ssf = new SSLSocketFactory(sc,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        
        //��������Ѿ�������������������֤����Ϣ�е�����ƥ�䣬�ſ��Կ���֤������У�� ��Ĭ��Ҳ�Ǵ򿪵ģ�
        //SSLSocketFactory ssf = new SSLSocketFactory(sc);
        
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 8743, ssf));
    }
    
    /**
     * ������֤���� One-way authentication
     * ������֤�����£��ͻ�����Ҫ
     *   1�����������CA֤�飬ʹ�÷����CA֤��У�����˷��͹�����֤��      
     *   2�����ò�У������  �������û����£��]��ʹ���������ʣ�
     *   */
    public void initSSLConfigForOneWay() throws Exception
    {
        //1�����������CA֤��
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        
        //2���ر�֤������У��  
        //(�������Ի����У�һ��û����������������ʹ��ip���з��ʵģ����ֳ����±���ر�֤�������У�鹦��)
        SSLSocketFactory ssf = new SSLSocketFactory(sc,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        
        //��������Ѿ�������������������֤����Ϣ�е�����ƥ�䣬�ſ��Կ���֤������У�� ��Ĭ��Ҳ�Ǵ򿪵ģ�
        //SSLSocketFactory ssf = new SSLSocketFactory(sc);
        
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 8743, ssf));
    }
    
    
    public String doPost(String url, Map<String, String> map, String charset)
    {
        HttpPost httpPost = null;
        String result = null;
        try
        {
            httpPost = new HttpPost(url); 
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext())
            {
                Entry<String, String> elem = iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0)
            {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = this.execute(httpPost);
            if (response != null)
            {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null)
                {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static void main(String args[]) throws Exception
    {
        //ʾ��1��˫����֤
        String url1 = "https://185.1.8.218:8743/iocm/app/sec/v1.1.0/login";
        Map<String, String> paramap = new HashMap<String, String>();
        paramap.put("appId", "1");
        paramap.put("secret", "12345");
        HttpsClientDemo httpClient1 = new HttpsClientDemo();
        httpClient1.initSSLConfigForTwoWay();
        String result1 = httpClient1.doPost(url1, paramap, "UTF-8");
        System.out.println(result1);
        
        //ʾ��2��������֤
        String url2 = "https://185.1.8.218:8643";
        HttpsClientDemo httpClient2 = new HttpsClientDemo();
        httpClient2.initSSLConfigForOneWay();
        String result2 = httpClient2.doPost(url2, paramap, "UTF-8");
        System.out.println(result2);
        
    }
    

}
