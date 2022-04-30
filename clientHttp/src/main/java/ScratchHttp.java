import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*** description:
 * reference:https://www.jianshu.com/p/848911df8052
 **/
public class ScratchHttp {

    public static void main(String[] args) {
        try {
            URL serverUrl = new URL("http://www.xihuanyuye.com");
            HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestProperty("Content-type", "application/json");
            //必须设置false，否则会自动redirect到重定向后的地址
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("GET");
            /**
             * make the connection
             */
            conn.connect();

            String result = getReturn(conn);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*请求url获取返回的内容*/

    /**
     * del the response content from the server
     *
     * @param connection
     * @return
     * @throws Exception
     */
    public static String getReturn(HttpURLConnection connection) throws Exception {
        StringBuffer buffer = new StringBuffer();
        /**
         * transform the byte back into string
         */
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str;
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
