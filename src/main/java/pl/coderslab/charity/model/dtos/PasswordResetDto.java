package pl.coderslab.charity.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.validation.FieldMatch;

import javax.validation.constraints.NotBlank;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "{field.match.validator.message}")
@NoArgsConstructor
public class PasswordResetDto {

    //TODO - password validation
    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}