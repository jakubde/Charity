package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserRepository;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public User dtoToEntity (UserDto userDto){

        User user = modelMapper.map(userDto, User.class);

//        for(RoleDto roleDto : userDto.getRoles()){
//            user.getRoles().add(modelMapper.map(roleDto, Role.class));
//        }

        return user;
    }

    public void saveUser(UserDto userDto){
        userRepository.save(dtoToEntity(userDto));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
