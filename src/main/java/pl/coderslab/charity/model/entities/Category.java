package pl.coderslab.charity.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
@Table(name = "categories")
public class Category extends BaseEntity {

    @NotBlank
    @Column(unique = true)
    private String name;
    
    @NotBlank
    private String nameEng;
}
