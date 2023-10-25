package dantesyh.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.*;
import java.util.Collections;
import java.util.List;

/**
 * 网络相关工具类
 *
 * @author dante
 * @since 2023/8/31
 */
public class NetworkUtils {
    private NetworkUtils() {

    }

    private static final String UNKNOWN = "unknown";

    /**
     * 获取本地计算机的IP地址。首先尝试从网卡返回机器IP，如果失败则尝试返回内部IP。
     *
     * @return 本地计算机的IP地址
     * @throws SocketException      如果发生套接字异常
     * @throws UnknownHostException 如果发生未知主机异常
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

    /**
     * 获取客户端的IP地址从HttpServletRequest对象中。
     *
     * @param request HttpServletRequest对象，包含客户端请求信息
     * @return 客户端的IP地址
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
