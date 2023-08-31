package dantesyh.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 网络相关工具类
 *
 * @author dante
 * @since 2023/8/31
 */
public class NetworkUtils {
    private NetworkUtils() {

    }

    /**
     * @return 优先尝试从网卡返回机器IP，再尝试返回内部IP
     */
    public static String getLocalIP() throws SocketException, UnknownHostException {
        List<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

        for (NetworkInterface ni : networkInterfaces) {
            if (!ni.isLoopback() && ni.isUp()) {
                List<InterfaceAddress> interfaceAddresses = ni.getInterfaceAddresses().stream()
                        .filter(address -> address.getAddress() instanceof Inet4Address)
                        .toList();

                if (!interfaceAddresses.isEmpty()) {
                    return interfaceAddresses.get(0).getAddress().getHostAddress();
                }
            }
        }

        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
