package demo.devspringboot.WebBackEnd.user.dto;

import demo.devspringboot.WebBackEnd.validation.anotation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOForSave implements Serializable {

    @Size(min=5, max=30)
    @NotBlank
    @NotNull
    @NotEmpty
    @UniqueUsername
    private String username;

    @Size(min=5, max=30)
    @NotBlank
    private String password;
    private int age;
    private String gender;
    private String roles;
}
