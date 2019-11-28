package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email = ?1")
    User findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email = ?1 AND enabled = TRUE")
    User findEnabledByEmail(String email);

    @Query(nativeQuery = true, value = "select count(user_id) from users WHERE enabled = TRUE")
    Integer countAllUsers();

    @Query(nativeQuery = true, value = "select * from users INNER join user_authorities ON users.email = user_authorities.email WHERE authority LIKE 'ROLE_ADMIN'")
    List<User> findAllAdmins();

    User findAllById(Long id);

    @Query(nativeQuery = true, value = "DELETE FROM user_authorities where email = ?1")
    @Modifying
    void deleteUserAuthorities(String email);
}
