package uz.everbest.buxorossb.service;

public interface QRCodeService {

    byte[] getQRCodeImage(String text);

    String generateQRCode(String code);
}
