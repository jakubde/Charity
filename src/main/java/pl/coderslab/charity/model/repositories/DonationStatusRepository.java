package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.DonationStatus;

import java.util.List;

public interface DonationStatusRepository extends JpaRepository<DonationStatus, Long> {
    DonationStatus findAllById(Long id);

    @Query(nativeQuery = true, value = "select id from donation_status where name = ?1")
    Long getIdByName(String donationStatusName);

    @Query(nativeQuery = true, value = "select name from donation_status")
    List<String> getNameList();

    DonationStatus findAllByName(String donationStatusName);
}
