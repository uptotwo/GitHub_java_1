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
 * 使用apcehe 的httpclient发送https消息的对接示例代码
 * 
 * 示例代码中使用的是httpclient4.2.6版本的DefaultHttpClient
 * 如果是httpclient4.3以上版本 ，请使用CloseableHttpClient替换
 * */
public class HttpsClientDemo extends DefaultHttpClient
{
    //示例代码  证书路径、证书密钥请根据实际情况替换
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
    public void initSSLConfigForTwoWay() throws Exception
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
        
        //3、关闭证书域名校验  
        //(联调测试环境中，一般没有申请域名，而是使用ip进行访问的，这种场景下必须关闭证书的域名校验功能)
        SSLSocketFactory ssf = new SSLSocketFactory(sc,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        
        //如果环境已经申请了域名，并且与证书信息中的域名匹配，才可以开启证书域名校验 （默认也是打开的）
        //SSLSocketFactory ssf = new SSLSocketFactory(sc);
        
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 8743, ssf));
    }
    
    /**
     * 单向认证场景 One-way authentication
     * 单向认证场景下，客户端需要
     *   1、导入服务器CA证书，使用服务端CA证书校验服务端发送过来的证书      
     *   2、设置不校验域名  （非商用环境下，沒有使用域名访问）
     *   */
    public void initSSLConfigForOneWay() throws Exception
    {
        //1、导入服务器CA证书
        KeyStore caCert = KeyStore.getInstance("jks");
        caCert.load(new FileInputStream(TRUSTCAPATH), TRUSTCAPWD.toCharArray());
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
        tmf.init(caCert);
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, tmf.getTrustManagers(), null);
        
        //2、关闭证书域名校验  
        //(联调测试环境中，一般没有申请域名，而是使用ip进行访问的，这种场景下必须关闭证书的域名校验功能)
        SSLSocketFactory ssf = new SSLSocketFactory(sc,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        
        //如果环境已经申请了域名，并且与证书信息中的域名匹配，才可以开启证书域名校验 （默认也是打开的）
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
        //示例1：双向认证
        String url1 = "https://185.1.8.218:8743/iocm/app/sec/v1.1.0/login";
        Map<String, String> paramap = new HashMap<String, String>();
        paramap.put("appId", "1");
        paramap.put("secret", "12345");
        HttpsClientDemo httpClient1 = new HttpsClientDemo();
        httpClient1.initSSLConfigForTwoWay();
        String result1 = httpClient1.doPost(url1, paramap, "UTF-8");
        System.out.println(result1);
        
        //示例2：单向认证
        String url2 = "https://185.1.8.218:8643";
        HttpsClientDemo httpClient2 = new HttpsClientDemo();
        httpClient2.initSSLConfigForOneWay();
        String result2 = httpClient2.doPost(url2, paramap, "UTF-8");
        System.out.println(result2);
        
    }
    

}
