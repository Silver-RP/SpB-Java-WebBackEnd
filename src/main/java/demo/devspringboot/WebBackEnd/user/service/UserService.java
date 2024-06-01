package demo.devspringboot.WebBackEnd.user.service;

import demo.devspringboot.WebBackEnd.common.service.GenericService;
import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import demo.devspringboot.WebBackEnd.user.dto.UserDTOForSave;
import demo.devspringboot.WebBackEnd.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User,UserDTO, UUID> {
    UserDTO save(UserDTOForSave userDTOForSave);
    UserDTO changeAvatar(String username, MultipartFile file);
    Optional<User> findByUsername(String username);
//    UserDTO login(String username, String password);
}
