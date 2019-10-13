package pl.coderslab.charity.model.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    private Boolean enabled;

    private Set<RoleDto> roles = new HashSet<>();
}

