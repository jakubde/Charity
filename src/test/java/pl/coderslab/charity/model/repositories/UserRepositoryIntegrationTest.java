package pl.coderslab.charity.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.embeddable.Role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void givenUser_whenSave_thenGetOk() {
        User userEntity = new User();
        userEntity.setEnabled(false);
        userEntity.setEmail("test@test.test");
        userEntity.setFirstName("firstName");
        userEntity.setLastName("lastName");
        userEntity.setPassword("password");
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        userEntity.getRoles().add(role);

        entityManager.persist(userEntity);
        entityManager.flush();

        User foundEntity = userRepository.findByEmail("test@test.test");

        assertNotNull(foundEntity);
        assertThat(userEntity.getLastName()).isEqualTo(foundEntity.getLastName());
    }
}