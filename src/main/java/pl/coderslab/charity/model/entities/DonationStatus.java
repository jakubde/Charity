package pl.coderslab.charity.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class DonationStatus extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String name;

    private String nameEng;
}
