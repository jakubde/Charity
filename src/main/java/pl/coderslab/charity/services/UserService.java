package pl.coderslab.charity.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.model.repositories.VerificationTokenRepository;
import pl.coderslab.charity.utils.ObjectMapper;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(UserDto userDto){

        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);
        return userRepository.save(objectMapper.convert(userDto, User.class));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public UserDto findUserbyEmail(String email){
        return objectMapper.convert(userRepository.findByEmail(email), UserDto.class);
    }

    public UserDto findEnabledUserByEmail(String email){

        User user = userRepository.findEnabledByEmail(email);

        if (user == null){
            return null;
        }
        else {
            return objectMapper.convert(user, UserDto.class);
        }
    }

    public User getUser(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    public void saveToken (VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findVerificationToken(String verificationToken){
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public void deleteToken(VerificationToken verificationToken){
        verificationTokenRepository.delete(verificationToken);
    }
}
