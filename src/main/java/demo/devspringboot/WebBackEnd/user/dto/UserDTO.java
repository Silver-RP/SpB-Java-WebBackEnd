package demo.devspringboot.WebBackEnd.user.dto;

import lombok.*;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;
    private String userName;
    private int age;
    private String gender;
}
