package pl.coderslab.charity.model.dtos;

import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationDto {

    @Id
    private Long id;

    private List<CategoryDto> categoryDtos;

    private InstitutionDto institutionDto;

    private String street;
    private String city;
    private String zipCode;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;

}
