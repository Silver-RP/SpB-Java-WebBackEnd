package demo.devspringboot.WebBackEnd.user.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.common.util.FileUtils;
import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import demo.devspringboot.WebBackEnd.user.dto.UserDTO;
import demo.devspringboot.WebBackEnd.user.dto.UserDTOForSave;
import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final WebBackEndMapper webBackEndMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, WebBackEndMapper webBackEndMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.webBackEndMapper =  webBackEndMapper;
    }

    public UserDTO save(UserDTOForSave userDTOForSave){
        User user = webBackEndMapper.map(userDTOForSave, User.class);
        user.setPassword(passwordEncoder.encode(userDTOForSave.getPassword()));
        return webBackEndMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO changeAvatar(String username, MultipartFile avatar) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new WBEBussinessException("User not found."));
        FileUtils.deleteFile(user.getAvatar());
        user.setAvatar(FileUtils.saveFile(avatar));
        return webBackEndMapper.map(userRepository.save(user), UserDTO.class);
    }

//    @Override
//    public UserDTO login(String username, String password) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new WBEBussinessException("User not found"));
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new WBEBussinessException("Wrong password");
//        }
//        UserDTO userDTO = webBackEndMapper.map(user, UserDTO.class);
//        userDTO.setToken(jwtUtils.generateToken(username));
//        return userDTO;
//    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return userRepository;
    }

    @Override
    public WebBackEndMapper getMapper() {
        return webBackEndMapper;
    }

}
