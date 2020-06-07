package pl.coderslab.charity.model.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "donations")
@AttributeOverride(name = "id", column = @Column(name = "donation_id"))
public class Donation extends BaseEntity {

    @Column(nullable = false)
    private Integer quantity;

    @ManyToMany(
            targetEntity = Category.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "donation_category",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String street;
    private String city;
    private String zipCode;
    private String telephoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;
    private String pickUpComment;

    @ManyToOne
    @JoinColumn(name = "donation_status_id")
    private DonationStatus donationStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
