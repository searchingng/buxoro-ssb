package uz.everbest.buxorossb.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class ServerUtil {

    @Value("${server.domain}")
    private String domain;

    @Value("${server.port}")
    private String port;

    public String getLocalServerUrl(){
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            return "http://" + address + ":" + port;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public String getServerUrl(){
        return domain;
    }
}
