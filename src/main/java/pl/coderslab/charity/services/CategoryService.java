package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.repositories.CategoryRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<CategoryDto> getList() {
        return objectMapper.convertAll(categoryRepository.findAll(), CategoryDto.class);
    }

    public CategoryDto findById(Long id) {
        return objectMapper.convert(categoryRepository.findAllById(id), CategoryDto.class);
    }

    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findAllById(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setNameEng(categoryDto.getNameEng());
        categoryRepository.save(category);
    }
}
