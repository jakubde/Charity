package pl.coderslab.charity.model.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.coderslab.charity.model.entities.embeddable.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity {

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    private Boolean enabled;

    @ElementCollection
    @CollectionTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "email", referencedColumnName = "email")
    )
    public Set<Role> roles = new HashSet<>();
}
