package demo.devspringboot.WebBackEnd.security.service;

import demo.devspringboot.WebBackEnd.common.exception.WBEBussinessException;
import demo.devspringboot.WebBackEnd.security.model.UserDetailImps;
import demo.devspringboot.WebBackEnd.user.model.User;
import demo.devspringboot.WebBackEnd.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserCustomDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserCustomDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =  userRepository.findByUsername(username)
               .orElseThrow(()->new WBEBussinessException("User not found."));

        return UserDetailImps.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(
                        Arrays.stream(user.getRoles().split(","))
                                .map(role -> {
                                    return new SimpleGrantedAuthority("WBE_"+ role.trim().toUpperCase());
                                } ).toList()

                )
                .build();
    }
}
