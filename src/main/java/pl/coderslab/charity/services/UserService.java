package pl.coderslab.charity.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.model.repositories.VerificationTokenRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    //Methods for registering an account through a website
    public User createNewAccount(UserDto userDto){

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

    public void saveToken (VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findVerificationToken(String verificationToken){
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public void deleteToken(VerificationToken verificationToken){
        verificationTokenRepository.delete(verificationToken);
    }

    public User getUser(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    public void sendEmailConfirmationMailInAppropriateLanguage(String locale, UserDto userDto, VerificationToken verificationToken){

        String subject = "";
        String text = "";

        if (locale.equals("pl")) {
            subject = "Potwierdzenie rejestracji";

            text = "Witaj " + userDto.getFirstName() + ",\n\n" +

                    "Dziękujemy za rejestrację. Aby aktywować konto, kliknij następujący link:\n" +
                    "http://localhost:8080/confirm-account?token=" + verificationToken.getToken() + "\n\n" +

                    "Zespół \"Oddam w dobre ręce\"\n\n" +

                    "Pomagając innym, pomagamy sobie, bo wszystko, co dajemy, zatacza koło i wraca do nas.\n" +
                    "~Flora Edwards";

        }
        if (locale.equals("en")) {
            subject = "Registration confirmation";

            text = "Hello " + userDto.getFirstName() + ",\n\n" +

                    "Thank you for signing up. To activate the account click on the following link:" +
                    "http://localhost:8080/confirm-account?token=" + verificationToken.getToken() + "\n\n" +

                    "\"Leave it in good hands\" team.\n\n" +

                    "In helping others, we shall help ourselves, for whatever good we give out completes the circle and comes back to us.\n" +
                    "~Flora Edwards";
        }

        emailService.sendSimpleMessage(userDto.getEmail(), subject, text);
    }


    //Admin dashboard methods
    public Integer countAllUsers(){
        return userRepository.countAllUsers();
    }


    //Users management methods
    public void createNewUser(UserDto userDto){

        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);
        userRepository.save(objectMapper.convert(userDto, User.class));
    }

    public List<UserDto> findAllUsers() {
        return objectMapper.convertAll(userRepository.findAllUsers(), UserDto.class);
    }


    //Admins management methods
    public void createNewAdmin(UserDto userDto){

        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        RoleDto roleAdmin = new RoleDto();
        roleAdmin.setAuthority("ROLE_ADMIN");
        userDto.getRoles().add(roleAdmin);
        userDto.setEnabled(true);
        userRepository.save(objectMapper.convert(userDto, User.class));
    }

    public List<UserDto> findAllAdmins() {
        return objectMapper.convertAll(userRepository.findAllAdmins(), UserDto.class);
    }

    public ModelAndView deleteAdmin(Long id, String currentUserEmail){
        ModelAndView modelAndView = new ModelAndView();
        if(userRepository.findAllById(id).getEmail().equals(currentUserEmail)){
            modelAndView.setViewName("admin/admins/selfDeleteError");
        }
        else {
            modelAndView.setViewName("redirect:/admins/edit");
            deleteUser(id);
        }
        return modelAndView;
    }


    //Utility methods
    public void updateUserDto(Long id, UserDto updatedUserDto){

        User currentUser = userRepository.findAllById(id);

        currentUser.setFirstName(updatedUserDto.getFirstName());
        currentUser.setLastName(updatedUserDto.getLastName());
        currentUser.setEnabled(updatedUserDto.getEnabled());

        userRepository.save(currentUser);
    }

    public UserDto findUserbyEmail(String email){

        User user = userRepository.findByEmail(email);

        if (user == null){
            return null;
        }
        else {
            return objectMapper.convert(user, UserDto.class);
        }
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

    public User dtoToEntity(UserDto userDto) {
        return objectMapper.convert(userDto, User.class);
    }

    public UserDto findUserById(Long id){
        return objectMapper.convert(userRepository.findAllById(id), UserDto.class);
    }

    public void deleteUser(Long id){
        userRepository.deleteUserAuthorities(userRepository.findAllById(id).getEmail());
        userRepository.deleteById(id);
    }
}
