package demo.devspringboot.WebBackEnd.user.restResource;

import demo.devspringboot.WebBackEnd.common.util.ResponseUtil;
import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import demo.devspringboot.WebBackEnd.user.dto.UserDTOForSave;
import demo.devspringboot.WebBackEnd.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserRestResource {
    UserService userService;

    public UserRestResource(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public Object register(@RequestBody @Valid UserDTOForSave userDTOForSave){
        return ResponseUtil.get(userService.save(userDTOForSave), HttpStatus.OK);
    }

    @PostMapping(value = "/{username}/changeAvatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object addAvatar(@PathVariable ("username") String username, @RequestPart("file") MultipartFile avatar){
        return ResponseUtil.get(userService.changeAvatar(username, avatar), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public Object findAllUser(){
        return ResponseUtil.get(userService.findAll(UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Object deleteUser(@RequestParam UUID id){
        userService.delete(id);
        return ResponseUtil.get("Delete Successfully", HttpStatus.OK);
    }
}
