package uz.everbest.buxorossb.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface UploadService {

    String upload(MultipartFile multipart);

    String thumbnail(String path);

    void delete(String path);

    byte[] getResource(String path);

    File getFile(String path);

}
