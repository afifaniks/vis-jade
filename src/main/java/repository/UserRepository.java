package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schema.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
    // Additional query methods can be added if needed
}