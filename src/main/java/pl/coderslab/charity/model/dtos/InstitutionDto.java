package pl.coderslab.charity.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class InstitutionDto {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
    @NotBlank
    private String nameEng;

    @NotBlank
    private String descriptionEng;
}
