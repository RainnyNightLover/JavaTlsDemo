import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/*** description:
 **/
public class MyHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String s, SSLSession sslsession) {
        /**
         * add logic later
         */
        return true;
    }

}
