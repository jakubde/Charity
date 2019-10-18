package pl.coderslab.charity.services;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.repositories.CategoryRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;


    public CategoryService(CategoryRepository categoryRepository, ObjectMapper objectMapper) {
        this.categoryRepository = categoryRepository;
        this.objectMapper = objectMapper;
    }

    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(objectMapper.convert(categoryDto, Category.class));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> getList(){
        return objectMapper.convertAll(categoryRepository.findAll(), CategoryDto.class);
    }

}
