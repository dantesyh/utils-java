package dantesyh.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * @author dante
 * @since 2023/8/31
 */
class NetworkUtilsTest {
    private static final Logger logger = Logger.getLogger(SecureUtils.class.getName());

    @Test
    void testLocalIP() throws UnknownHostException, SocketException {
        String localIP = NetworkUtils.getLocalIP();
        logger.info("Local IP : " + localIP);
        Assertions.assertNotNull(localIP);
    }

    @Test
    void testClientIP(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("X-Forwarded-For", "1.2.3.4");
        request.addHeader("Proxy-Client-IP", "5.6.7.8");
        request.setRemoteAddr("9.10.11.12");

        String clientIp = NetworkUtils.getClientIp(request);
        logger.info("Client IP: " + clientIp);
        Assertions.assertEquals("1.2.3.4", clientIp);
    }
}