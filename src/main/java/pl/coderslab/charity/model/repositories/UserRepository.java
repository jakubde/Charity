package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email = ?1")
    User findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email = ?1 AND enabled = TRUE")
    User findEnabledByEmail(String email);

    @Query(nativeQuery = true, value = "select count(user_id) from users WHERE enabled = TRUE")
    Integer countAllUsers();
}
