package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
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
        return modelMapper.map(userDto, User.class);
    }

    public UserDto entityToDto (User user){
        return  modelMapper.map(user, UserDto.class);
    }

    public void saveUser(UserDto userDto){
        userRepository.save(dtoToEntity(userDto));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public UserDto findUserbyEmail(String email){
        return entityToDto(userRepository.findByEmail(email));
    }
}
