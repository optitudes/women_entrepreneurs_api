package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

Boolean uploadProfileImage(MultipartFile image, String email) throws Exception;
byte[] getProfileImage(String imageName) throws Exception;
}
