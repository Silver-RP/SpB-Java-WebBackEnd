package demo.devspringboot.WebBackEnd.user.service;

import demo.devspringboot.WebBackEnd.common.util.WebBackEndMapper;
import demo.devspringboot.WebBackEnd.user.dto.UserDTOForSave;
import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    UserRepository userRepository ;
    WebBackEndMapper webBackEndMapper;

    public UserServiceImpl(UserRepository userRepository, WebBackEndMapper webBackEndMapper){
        this.userRepository = userRepository;
        this.webBackEndMapper =  webBackEndMapper;
    }

    public User save(UserDTOForSave userDTOForSave){
        User user = webBackEndMapper.map(userDTOForSave, User.class);
        userRepository.save(user);
        return user;
    }

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
