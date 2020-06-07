package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.Donation;

import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Long> {

    Donation findAllById(Long id);

    @Query(nativeQuery = true, value = "select sum(quantity) from donations")
    Integer sumAllDonations();

    @Query(nativeQuery = true, value = "select count(DISTINCT institution_id) from donations")
    int countAllDistinctInstitutions();

    @Query(nativeQuery = true, value = "select pick_up_date from donations order by pick_up_date limit 1")
    String firstDonationDate();

    @Query(nativeQuery = true, value = "select sum(quantity) from donations where pick_up_date < ?1")
    Integer sumDonationsWhereDateComesBefore(String date);

    @Query(nativeQuery = true, value = "select sum(quantity) from donations where institution_id = ?1")
    Integer sumOfDonationsPerInstitution(Long id);

    @Query(nativeQuery = true, value = "select category_id from donation_category where donation_id = ?1")
    List<Long> findAllCategoryIdsByDonationId(Long donationId);

    @Modifying
    @Query(nativeQuery = true, value = "delete from donation_category where donation_id = ?1")
    void deleteRelatedDataFromDonationCategoryTable(Long donationId);

    @Query(nativeQuery = true, value = "select sum(quantity) from donations where pick_up_date like ?1%")
    String getDonationsSumInGivenMonth(String dateToQueryInRepository);

}
