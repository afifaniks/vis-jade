package vis.services;


import schema.User;

import java.util.List;
import java.util.Optional;

public interface DatabaseService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
