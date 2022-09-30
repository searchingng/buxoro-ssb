package uz.everbest.buxorossb.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class ServerUtil {

    @Value("${server.port}")
    private String port;

    public String getServerUrl(){
        try {
            String address = InetAddress.getLocalHost().getHostAddress();
            return "http://" + address + ":" + port;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
