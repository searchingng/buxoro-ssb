package uz.everbest.buxorossb.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uz.everbest.buxorossb.service.QRCodeService;
import uz.everbest.buxorossb.service.UploadService;
import uz.everbest.buxorossb.util.ServerUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    private final int SIZE = 200;
    private final String EXTENSION = "png";
    private final UploadService uploadService;
    private final ServerUtil serverUtil;

    @Value("${upload.folder}")
    private String uploadFolder;

    @Override
    public byte[] getQRCodeImage(String code) {
        String path = getPath(code);
        return uploadService.getResource(path);
    }

    @Override
    public String generateQRCode(String code) {
        String text = serverUtil.getServerUrl() + "/api/forms/by-code?code=" + code;
        String path = getPath(code);
        code = String.join(" ", code.substring(0, 3), code.substring(3, 6), code.substring(6));
        BufferedImage image = getBufferedImage(code, text);
        File file = new File(uploadFolder + "/qr-codes");
        if (!file.exists())
            file.mkdirs();

        file = new File(uploadFolder + path);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ImageIO.write(image, EXTENSION, outputStream);
            outputStream.close();
            return path;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in creating QR Code");
        }
    }

    private BufferedImage getBufferedImage(String code, String text) {

        Hashtable<EncodeHintType, ErrorCorrectionLevel> hints = new Hashtable<>();
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, SIZE, SIZE, hints);
            BufferedImage image = new BufferedImage(SIZE, SIZE + 20, BufferedImage.TYPE_INT_ARGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();

            Color black = new Color(0, 0, 0, 255);
            Color white = new Color(255, 255, 255, 255);

            graphics.setColor(white);
            graphics.fillRoundRect(0, 0, SIZE, SIZE + 20, 20, 20);

            graphics.setColor(black);
            for (int i = 0; i < SIZE; i++){
                for (int j = 0; j < SIZE; j++){
                    if (matrix.get(i, j))
                        graphics.fillRect(i, j, 1, 1);
                }
            }

            Font font = new Font("Courier New", Font.BOLD, SIZE/10);
            graphics.setFont(font);
            graphics.drawString(code, 33, SIZE-15);

            return image;
        } catch (WriterException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error in creating QR Code");
        }
    }

    private String getPath(String code){
        return "/qr-codes/" + code + "." + EXTENSION;
    }

}
