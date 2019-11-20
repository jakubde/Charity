package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.Institution;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Institution findAllById (Long id);

//    @Query(nativeQuery = true, value = "select id from institutions")
//    List<Institution> findAllIds();

}
