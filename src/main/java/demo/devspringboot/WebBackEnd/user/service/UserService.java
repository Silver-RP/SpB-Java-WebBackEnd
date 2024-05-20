package demo.devspringboot.WebBackEnd.user.service;

import demo.devspringboot.WebBackEnd.common.service.GenericService;
import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import demo.devspringboot.WebBackEnd.user.dto.UserDTOForSave;
import demo.devspringboot.WebBackEnd.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User,UserDTO, UUID> {
    User save(UserDTOForSave userDTOForSave);
    Optional<User> findByUsername(String username);
}
