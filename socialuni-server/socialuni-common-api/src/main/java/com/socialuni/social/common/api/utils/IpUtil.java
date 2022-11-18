package com.socialuni.social.common.api.utils;

import com.socialuni.social.common.api.constant.SocialWebHeaderName;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
    public static String getIpAddr() {
        HttpServletRequest request = RequestUtil.getRequest();
        return IpUtil.getIpAddr(request);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            ipAddress = "123.119.137.133";
        } else {
            ipAddress = RequestUtil.getHeader(SocialWebHeaderName.requestIpHeaderName);
            if (StringUtils.isEmpty(ipAddress)) {
                try {
                    ipAddress = request.getHeader("x-forwarded-for");
                    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                        ipAddress = request.getHeader("Proxy-Client-IP");
                    }
                    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                        ipAddress = request.getHeader("WL-Proxy-Client-IP");
                    }
                    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                        ipAddress = request.getRemoteAddr();
                        if (ipAddress.equals("127.0.0.1")) {
                            // 根据网卡取本机配置的IP
                            InetAddress localIp = null;
                            try {
                                localIp = InetAddress.getLocalHost();
                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            }
                            if (!ObjectUtils.isEmpty(localIp)) {
                                ipAddress = localIp.getHostAddress();
                            }
                        }
                    }
                    // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
                    if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                        // = 15
                        if (ipAddress.indexOf(",") > 0) {
                            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                        }
                    }
                } catch (Exception e) {
                    ipAddress = "";
                }
            }
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        return ipAddress;
    }
}