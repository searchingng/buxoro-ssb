package uz.everbest.buxorossb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.service.UploadService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        int heighSize = 130;
        int widthSize = 154;
        File file = getFile(path);
        if (!file.exists())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image not found for thumbnail");

        try {
            BufferedImage source = ImageIO.read(file);
            int w = source.getWidth();
            int h = source.getHeight();

            if (w > h){
                w = Math.round(1f*w / (1f*h / heighSize));
                h = heighSize;
            } else {
                h = Math.round(1f*h / (1f*w / widthSize));
                w = widthSize;
            }

            String thumbPath = path.substring(0, path.lastIndexOf("/") + 1);
            String uuid = UUID.randomUUID().toString();
            String extension = getExtensionWithDot(path);
            thumbPath += uuid + extension;

            File output = new File(uploadFolder + thumbPath);
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            image.createGraphics().drawImage(source.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);
            ImageIO.write(image, extension.substring(1), output);

            return thumbPath;

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Thumbnail was not created; " + e.getMessage());
        }
    }

    @Override
    public void delete(String path) {
        File file = getFile(path);
        if (file.exists() && !file.delete())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File was not deleted");
    }

    @Override
    public byte[] getResource(String path) {
        try(InputStream inputStream = new FileInputStream(uploadFolder + path)){
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File Not Found");
        }
    }

    @Override
    public File getFile(String path){
        return new File(uploadFolder + path);
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
