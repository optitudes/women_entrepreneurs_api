package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//este dto esta hecho para obtener la informacion necesaria en la vista de perfile
public class UserProfileDTO {
    @Nullable
    private String imageUrl;
    private  String  names;
    private String lastNames;
    private String email;
    @Nullable
    private String address;
    @Nullable
    private Long phoneNumber;
    private String idNumber;
    private String accessName;
    private String accessDescription;
}
