package pl.coderslab.charity.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
public class DonationStatusDto {

    @Id
    private Long id;

    private String name;
}
