package pl.coderslab.charity.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.charity.CharityApplication;
import pl.coderslab.charity.model.entities.User;
import pl.coderslab.charity.model.entities.embeddable.Role;
import pl.coderslab.charity.model.repositories.UserRepository;
import pl.coderslab.charity.services.UserService;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CharityApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc

public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    private RegistrationController registrationController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    public void contextLoads() {
        assertThat(registrationController).isNotNull();
    }

    @Test
    public void registerUserGet_status200() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void registerUserPost_invalidDataFirstPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "firstName", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "lastName", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "email", "NotBlank"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "NotBlank"))
                .andExpect(model().errorCount(4))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_invalidDataSecondPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML)
                .param("firstName", "firstNameLongerThanThirtyTwoCharacters")
                .param("lastName", "lastNameLongerThanThirtyTwoCharacters")
                .param("email", "badlyFormattedEmail")
                .param("password", "PASSWORD_WITHOUT_LOWERCASE_1")
                .param("confirmPassword", "PASSWORD_WITHOUT_LOWERCASE_1"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "firstName", "Size"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "lastName", "Size"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "email", "Email"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "Pattern"))
                .andExpect(model().errorCount(4))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_invalidDataThirdPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML)
                .param("firstName", "firstNameNotOnlyLetters123")
                .param("lastName", "lastNameNotOnlyLetters123")
                .param("email", "properly@formatted.email")
                .param("password", "password_without_uppercase_1")
                .param("confirmPassword", "password_without_uppercase_1"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "firstName", "Pattern"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "lastName", "Pattern"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "Pattern"))
                .andExpect(model().errorCount(3))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_invalidDataFourthPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML)
                .param("firstName", "validFirstName")
                .param("lastName", "validLastName")
                .param("email", "properly@formatted.email")
                .param("password", "passwordWithoutAnyDigit")
                .param("confirmPassword", "passwordWithoutAnyDigit"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "Pattern"))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_invalidDataFifthPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML)
                .param("firstName", "validFirstName")
                .param("lastName", "validLastName")
                .param("email", "properly@formatted.email")
                .param("password", "Password1")
                .param("confirmPassword", "Password1"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "NotOnWeakPasswordList"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_invalidDataSixthPart_hasFieldErrorAndStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/register")
                .accept(MediaType.TEXT_HTML)
                .param("firstName", "validFirstName")
                .param("lastName", "validLastName")
                .param("email", "properly@formatted.email")
                .param("password", "nonMatchingPassword1")
                .param("confirmPassword", "nonMatchingPassword2"))
                .andExpect(model().attributeHasFieldErrorCode("userDto", "password", "FieldMatch"))
                .andExpect(model().errorCount(1))
                .andExpect(view().name("/registration/register"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void registerUserPost_validData_status200() throws Exception {

        //Testing only validation, not further methods
        doNothing().when(userService).createNewUser(any());
        doNothing().when(userService).sendRegistrationEmailWithToken(any(),any());

            mockMvc.perform(MockMvcRequestBuilders
                    .post("/register")
                    .accept(MediaType.TEXT_HTML)
                    .param("firstName", "validFirstName")
                    .param("lastName", "validLastName")
                    .param("email", "properly@formatted.email")
                    .param("password", "matchingPassword1")
                    .param("confirmPassword", "matchingPassword1"))
                    .andExpect(model().hasNoErrors())
                    .andExpect(status().isOk())
                    .andDo(print());
    }
}