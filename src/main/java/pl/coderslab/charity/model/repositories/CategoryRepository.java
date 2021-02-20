package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.model.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findAllById(Long id);
}