package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.Donation;


public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(nativeQuery = true, value = "select sum(quantity) from donations")
    Integer sumAllDonations();

    @Query(nativeQuery = true, value = "select count(DISTINCT institution_id) from donations")
    int countAllDistinctInstitutions();

}
