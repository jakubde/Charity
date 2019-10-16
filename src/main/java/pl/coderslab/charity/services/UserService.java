package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User dtoToEntity (UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto entityToDto (User user){
        return  modelMapper.map(user, UserDto.class);
    }

    public void saveUser(UserDto userDto){

        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());//do serwisu
        userDto.setPassword(encodedPassword);//do serwisu
        userDto.setEnabled(true);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);
        userRepository.save(dtoToEntity(userDto));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserDto findUserbyEmail(String email){
        return entityToDto(userRepository.findByEmail(email));
    }
}
