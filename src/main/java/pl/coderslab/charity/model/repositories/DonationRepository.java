package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.entities.Donation;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(nativeQuery = true, value = "select sum(quantity) from donations")
    Integer sumAllDonations();

    @Query(nativeQuery = true, value = "select count(DISTINCT institution_id) from donations")
    int countAllDistinctInstitutions();

//    @Modifying
//    @Query(value = "update charity_donation.donations set institution_id = :institutionId WHERE id = :id", nativeQuery = true)
//    @Transactional
//    void updateInstitutionId(@Param("institutionId") Long institutionId, @Param("id") Long id);


    @Modifying
    @Query(value = "INSERT INTO charity_donation.donations (city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, telephone_number, institution_id) VALUES (:city, :pick_up_comment, :pick_up_date, :pick_up_time, :quantity, :street, :zip_code, :telephone_number, :institutionId);", nativeQuery = true)
    @Transactional
    void saveWholeEntity(@Param("city") String city, @Param("pick_up_comment") String pickUpComment, @Param("pick_up_date") LocalDate pickUpDate, @Param("pick_up_time") LocalTime pickUpTime, @Param("quantity") Integer quantity, @Param("street") String street,  @Param("zip_code") String zipCode, @Param("telephone_number") String telephoneNumber, @Param("institutionId") Long institutionId);




    @Modifying
    @Query(value = "insert into charity_donation.donation_category (donation_id, category_id) VALUES (:donationId,:categoryId)", nativeQuery = true)
    @Transactional
    void saveDonationIdCategoryId(@Param("donationId") Long donationId, @Param("categoryId") Long categoryId);


}
