package demo.devspringboot.WebBackEnd.user.repository;

import demo.devspringboot.WebBackEnd.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    void deleteById(UUID id);
}
