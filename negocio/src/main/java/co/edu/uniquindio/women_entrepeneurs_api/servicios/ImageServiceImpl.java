package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import co.edu.uniquindio.women_entrepeneurs_api.repo.LevelAccessRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.ProfileRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{

    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    private final LevelAccessRepo levelAccessRepo;
    private  MailServiceImpl mailService;


    public ImageServiceImpl(UserRepo userRepo, LevelAccessRepo levelAccessRepo, ProfileRepo profileRepo, MailServiceImpl mailService) {
        this.userRepo = userRepo;
        this.levelAccessRepo = levelAccessRepo;
        this.profileRepo = profileRepo;
        this.mailService = mailService;
    }


    @Override
    public Boolean uploadProfileImage(MultipartFile image, String email) throws Exception {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty())
            throw new Exception("Usuario no encontrado");
        Profile profile = userOptional.get().getProfile();
        String imageName = "profile"+userOptional.get().getId();
        String imageSavedName = saveImage(image,imageName,"uploads/pictures/profilePictures");
        String fullImagePath = "https://beta.api.turismoenlacordillera.com/api/user/public/getProfilePicture/"+imageSavedName;
        profile.setPicture_url(fullImagePath);
        profileRepo.save(profile);
        return true;
    }

    private String saveImage(MultipartFile image, String imageName, String imagePath) throws Exception {
        byte[] imageBytes = image.getBytes();
        String imageExtension = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
        long imageSize = image.getSize();
        long maxFileSize = 5*1024*1024;
        if (imageSize > maxFileSize)
            throw new Exception("El tamanio excede el permitido");
        if (
                !imageExtension.equals(".jpg") &&
                        !imageExtension.equals(".JPG") &&
                        !imageExtension.equals(".png") &&
                        !imageExtension.equals(".PNG")
        )
            throw  new Exception("La extension debe ser jpg o png");
        String newImageName = imageName+imageExtension;

        File profileImageFolder = new File(imagePath);
        if(!profileImageFolder.exists()){
            boolean wasCreated = profileImageFolder.mkdir();
            if (!wasCreated)
                throw new Exception("ocurrio un error, verifique  los permisos de escritura en uploads");
        }
        Path pathToSave = Paths.get(imagePath+"/"+newImageName);
        Files.write(pathToSave,imageBytes);
        return newImageName;
    }

    @Override
    public byte[] getProfileImage(String imageNameAux) throws Exception {
        String imageName = cleanImageName(imageNameAux);
        return getImageBytes ("uploads/pictures/profilePictures/",imageName) ;
    }

    @Override
    public byte[] getAppImage(String imageNameAux) throws Exception {
        String imageName = cleanImageName(imageNameAux);
        return getImageBytes ("uploads/pictures/app/",imageName) ;
    }

    private byte[] getImageBytes(String folderPath, String imageName) throws IOException {
        Path path = Paths.get(folderPath+imageName);
        return Files.readAllBytes(path);
    }

    //metodo que elimina los caracteres especiales de un string y coloca un . antes
    //de los ultimos tres caracteres para denotar la extension .jpg .png
    private String cleanImageName(String imageNameAux) {
        String imageName = imageNameAux.replaceAll("[^a-zA-Z0-9]","");
        String extension = imageName.substring(imageName.length() - 3);
        extension = "." +extension;
        return imageName.substring(0,imageName.length()-3)+extension ;
    }
}
