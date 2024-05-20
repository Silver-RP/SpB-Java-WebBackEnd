package demo.devspringboot.WebBackEnd.user.repository;

import demo.devspringboot.WebBackEnd.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User save(User user);
    public List<User> findAll();
    public Optional<User> findById(UUID id);
    public Optional<User> findByUsername(String username);
    public void deleteById(UUID id);
}
