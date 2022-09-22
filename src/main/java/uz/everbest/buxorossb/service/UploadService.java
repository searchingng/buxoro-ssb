package uz.everbest.buxorossb.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String upload(MultipartFile multipart);

    String thumbnail(String path);

    void delete(String path);

    byte[] getFile(String path);

}
