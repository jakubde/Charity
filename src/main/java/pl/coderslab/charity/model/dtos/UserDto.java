package pl.coderslab.charity.model.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.validation.FieldMatch;
import pl.coderslab.charity.validation.NotOnWeakPasswordList;
import pl.coderslab.charity.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "confirmPassword", message = "{field.match.validator.message}")
public class UserDto {

    private Long id;

    @NotBlank(message = "{last.name.not.blank}")
    @Size(max = 32, message = "{last.name.maximum.length}")
    @Pattern(regexp = "\\p{L}*", message = "{last.name.letters.only}")
    private String lastName;

    @NotBlank(message = "{first.name.not.blank}")
    @Size(max = 32, message = "{first.name.maximum.length}")
    @Pattern(regexp = "\\p{L}*", message = "{first.name.letters.only}")
    private String firstName;

    @NotBlank(message = "{email.not.blank}")
    @UniqueEmail(message = "{email.unique}")
    @Email(message = "{email.validator.message}")
    private String email;

    @NotBlank(message = "{password.not.blank}")
    @Size.List({
            @Size(min = 8, message = "{password.minimum.length}"),
            @Size(max = 32, message = "{password.maximum.length}")
    })
    @Pattern.List({
            @Pattern(regexp = "(?=.*[0-9]).+", message = "{password.at.least.one.digit}"),
            @Pattern(regexp = "(?=.*[\\p{Ll}]).+", message = "{password.at.least.one.lowercase}"),
            @Pattern(regexp = "(?=.*[\\p{Lu}]).+", message = "{password.at.least.one.uppercase}"),
            @Pattern(regexp = "(?=\\S+$).+", message = "{password.no.whitespace}")
    })
    @NotOnWeakPasswordList(message = "{password.too.weak}")
    private String password;

    private String confirmPassword;

    private Boolean enabled;

    private Set<RoleDto> roles = new HashSet<>();
}

