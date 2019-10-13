package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
