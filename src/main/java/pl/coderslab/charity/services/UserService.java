package pl.coderslab.charity.services;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.charity.model.dtos.PasswordResetDto;
import pl.coderslab.charity.model.dtos.RoleDto;
import pl.coderslab.charity.model.dtos.UserDto;
import pl.coderslab.charity.model.entities.PasswordResetToken;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.VerificationToken;
import pl.coderslab.charity.model.repositories.PasswordResetTokenRepository;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.model.repositories.VerificationTokenRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, PasswordResetTokenRepository passwordResetTokenRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder, /*BCryptPasswordEncoder bCryptPasswordEncoder,*/ EmailService emailService) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    //Methods for registering an account through a website
    public User createNewAccount(UserDto userDto) {
        //TODO - does it have to be null here?
        userDto.setId(null);
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
//        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());

        userDto.setPassword(encodedPassword);
        RoleDto roleUser = new RoleDto();
        roleUser.setAuthority("ROLE_USER");
        userDto.getRoles().add(roleUser);

        return userRepository.save(objectMapper.convert(userDto, User.class));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void saveVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

    public User getUserByVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    public void sendRegistrationEmailWithToken(Locale locale, UserDto userDto) {

        String subject = "";
        String text = "";
        String language = locale.getLanguage();

        VerificationToken verificationToken = new VerificationToken(dtoToEntity(findUserByEmail(userDto.getEmail())));
        saveVerificationToken(verificationToken);

        if (language.equals("pl")) {
            subject = "Potwierdzenie rejestracji";

            text = "Witaj " + userDto.getFirstName() + ",\n\n" +

                    "Dziękujemy za rejestrację. Aby aktywować konto, kliknij następujący link:\n" +
                    "http://localhost:8080/confirm-account?token=" + verificationToken.getToken() + "\n\n" +

                    "Zespół \"Oddam w dobre ręce\"\n\n" +

                    "Pomagając innym, pomagamy sobie, bo wszystko, co dajemy, zatacza koło i wraca do nas.\n" +
                    "~Flora Edwards";

        }
        if (language.equals("en")) {
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

    //Methods for changing the password
    public void savePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    public User getUserByPasswordResetToken(String passwordResetToken) {
        return passwordResetTokenRepository.findByToken(passwordResetToken).getUser();
    }

    public void sendPasswordResetEmailInAppropriateLanguage(Locale locale, UserDto userDto, PasswordResetToken passwordResetToken) {

        String subject = "";
        String text = "";
        String language = locale.getLanguage();

        if (language.equals("pl")) {
            subject = "Resetowanie hasła";

            text = "Witaj " + userDto.getFirstName() + ",\n\n" +

                    "Aby zresetować hasło, kliknij następujący link:\n" +
                    "http://localhost:8080/password-reset/token-validation?token=" + passwordResetToken.getToken() + "\n" +
                    "Jeżeli nie żądałeś zmiany hasła zignoruj tę wiadomość.\n\n" +

                    "Zespół \"Oddam w dobre ręce\"\n\n" +

                    "Pomagając innym, pomagamy sobie, bo wszystko, co dajemy, zatacza koło i wraca do nas.\n" +
                    "~Flora Edwards";

        }
        if (language.equals("en")) {
            subject = "Password reset";

            text = "Hello " + userDto.getFirstName() + ",\n\n" +

                    "To reset your password, click the following link:\n" +
                    "http://localhost:8080/password-reset/token-validation?token=" + passwordResetToken.getToken() + "\n" +
                    "If you have not requested a password change, ignore this message.\n\n" +

                    "\"Leave it in good hands\" team.\n\n" +

                    "In helping others, we shall help ourselves, for whatever good we give out completes the circle and comes back to us.\n" +
                    "~Flora Edwards";
        }

        emailService.sendSimpleMessage(userDto.getEmail(), subject, text);
    }

    public PasswordResetToken findPasswordResetToken(String passwordResetToken) {
        return passwordResetTokenRepository.findByToken(passwordResetToken);
    }

    public void changePassword(String token, PasswordResetDto passwordResetDto) {
        System.out.println(token);
        User user = getUserByPasswordResetToken(token);

        String newPassword = passwordResetDto.getPassword();
//        String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    //Admin dashboard methods
    public Integer countAllUsers() {
        return userRepository.countAllUsers();
    }


    //Users management methods
    public void createNewUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) == null) {
//            String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);
            userDto.setEnabled(false);
            RoleDto roleUser = new RoleDto();
            roleUser.setAuthority("ROLE_USER");
            userDto.getRoles().add(roleUser);
            userRepository.save(objectMapper.convert(userDto, User.class));
        } else if (!userDto.getEnabled()) {
//            String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodedPassword);
            userDto.setEnabled(false);
            userRepository.save(objectMapper.convert(userDto, User.class));
        }
    }

    public List<UserDto> findAllUsers() {
        return objectMapper.convertAll(userRepository.findAllUsers(), UserDto.class);
    }


    //Admins management methods
    public void createNewAdmin(UserDto userDto) {
//        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
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

    public ModelAndView deleteAdmin(Long id, String currentUserEmail) {
        ModelAndView modelAndView = new ModelAndView();
        if (userRepository.findAllById(id).getEmail().equals(currentUserEmail)) {
            modelAndView.setViewName("admin/admins/selfDeleteError");
        } else {
            modelAndView.setViewName("redirect:/admins/edit");
            deleteUser(id);
        }
        return modelAndView;
    }


    //Utility methods
    public void updateUserDto(Long id, UserDto updatedUserDto) {
        User currentUser = userRepository.findAllById(id);

        currentUser.setFirstName(updatedUserDto.getFirstName());
        currentUser.setLastName(updatedUserDto.getLastName());
        currentUser.setEnabled(updatedUserDto.getEnabled());

        userRepository.save(currentUser);
    }

    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;
        } else {
            return objectMapper.convert(user, UserDto.class);
        }
    }

    public UserDto findEnabledUserByEmail(String email) {
        User user = userRepository.findEnabledByEmail(email);

        if (user == null) {
            return null;
        } else {
            return objectMapper.convert(user, UserDto.class);
        }
    }

    public User dtoToEntity(UserDto userDto) {
        return objectMapper.convert(userDto, User.class);
    }

    public UserDto findUserById(Long id) {
        return objectMapper.convert(userRepository.findAllById(id), UserDto.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUserAuthorities(userRepository.findAllById(id).getEmail());
        userRepository.deleteById(id);
    }

    public List<String> getUserEmailList() {
        return userRepository.getEmailList();
    }

    public String getUserEmailById(Long userId) {
        return userRepository.getEmailById(userId);
    }
}
