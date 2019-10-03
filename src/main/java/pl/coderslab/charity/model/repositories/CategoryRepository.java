package pl.coderslab.charity.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.charity.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
