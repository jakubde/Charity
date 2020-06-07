package pl.coderslab.charity.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DonationDto {

    @Id
    private Long id;

    private Integer quantity;

    private List<Long> categoryIdList = new ArrayList<>();

    private Long institutionId;

    private Long donationStatusId;

    private Long userId;

    private String street;
    private String city;
    private String zipCode;
    private String telephoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;
    private String pickUpComment;

}
