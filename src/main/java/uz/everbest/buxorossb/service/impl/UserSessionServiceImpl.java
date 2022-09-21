package uz.everbest.buxorossb.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uz.everbest.buxorossb.dto.UserSessionDto;
import uz.everbest.buxorossb.entity.User;
import uz.everbest.buxorossb.entity.UserSession;
import uz.everbest.buxorossb.repository.UserSessionRepository;
import uz.everbest.buxorossb.service.UserSessionService;
import uz.everbest.buxorossb.service.mapper.UserSessionMapper;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionMapper userSessionMapper;
    private final UserSessionRepository userSessionRepository;

    public UserSessionServiceImpl(UserSessionMapper userSessionMapper, UserSessionRepository userSessionRepository) {
        this.userSessionMapper = userSessionMapper;
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public Page<UserSessionDto> findAll(Pageable pageable, User user_id) {
       return userSessionRepository.findAllByUserId(user_id.getId(), pageable).map(userSessionMapper::toDto);
    }

    @Override
    public void save(HttpServletRequest request, User user, String token) {
        UserSession userSession = new UserSession();
        userSession.setUser(user);
        userSession.setToken(token);
        userSession.setDeviceIp(getClientIpAddress(request));
        userSession.setDeviceModel(getDeviceModel(request));
        userSession.setDeviceOsVersion(getDeviceOsVersion(request));
        userSessionRepository.save(userSession);
    }

//    @Override
//    public UserSessionDto findOne(Long id) {
//        return adminSessionsRepository.findById(id).map(adminSessionMapper::toDto).get();
//    }

    private String getDeviceModel(HttpServletRequest request) {
        return request.getHeader("device-model");
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String LOCALHOST_IPV4 = "127.0.0.1";
        String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    private String getDeviceOsVersion(HttpServletRequest request) {
        return request.getHeader("device-os");
    }
}
