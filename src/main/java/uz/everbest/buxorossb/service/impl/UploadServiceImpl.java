package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.service.UploadService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    @Value("${upload.folder}")
    private String uploadFolder;

    @Override
    public String upload(MultipartFile multipart) {
        String uuid = UUID.randomUUID().toString();
        String path = getLocalPath();

        File file = new File(uploadFolder + path);

        if (!file.exists() && !file.mkdirs())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Permission to save file");

        path += uuid + getExtensionWithDot(Objects.requireNonNull(multipart.getOriginalFilename()));
        file = new File(uploadFolder + path);

        try {
            multipart.transferTo(file);
            return path;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "file was not uploaded");
        }
    }

    @Override
    public String thumbnail(String path) {
        return null;
    }

    @Override
    public void delete(String path) {
        File file = new File(uploadFolder + path);
        if (file.exists() && !file.delete())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File was not deleted");
    }

    @Override
    public byte[] getFile(String path) {
        try(InputStream inputStream = new FileInputStream(uploadFolder + path)){
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File Not Found");
        }
    }

    public String getLocalPath(){
        LocalDate date = LocalDate.now();
        return date.format(DateTimeFormatter.ofPattern("/yyyy/MM/"));
    }

    public String getExtensionWithDot(String fileName){
        int dot = fileName.lastIndexOf('.');
        return fileName.substring(dot);
    }

}
