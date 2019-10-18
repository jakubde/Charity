package pl.coderslab.charity.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.utils.ObjectMapper;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDto userDto){

        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        userDto.setEnabled(true);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);
        userRepository.save(objectMapper.convert(userDto, User.class));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserDto findUserbyEmail(String email){
        return objectMapper.convert(userRepository.findByEmail(email), UserDto.class);
    }

}
