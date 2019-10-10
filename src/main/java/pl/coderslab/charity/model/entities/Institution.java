package pl.coderslab.charity.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "institutions")
public class Institution extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String description;
}
