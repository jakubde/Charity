package pl.coderslab.charity.model.dtos;

import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class InstitutionDto {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
