import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/*** description:
 * reference:https://www.jianshu.com/p/848911df8052
 **/
public class ScratchHttps {

    public static void main(String[] args) throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
        URL serverUrl = new URL("https://www.xihuanyuye.com");
        HostnameVerifier ignoreHostnameVerifier = new MyHostnameVerifier();
        HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        //之后任何Https协议网站皆能正常访问
        HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //必须设置false，否则会自动redirect到重定向后的地址
        conn.setInstanceFollowRedirects(false);
        conn.connect();

        String result = getReturn(conn);
        System.out.println(result);
    }

    /**
     * del the response content from the server
     *
     * @param connection
     * @return
     * @throws Exception
     */
    public static String getReturn(HttpsURLConnection connection) throws Exception {
        StringBuffer buffer = new StringBuffer();
        /**
         * transform the byte back into string
         */
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

}
