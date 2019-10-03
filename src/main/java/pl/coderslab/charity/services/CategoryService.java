package pl.coderslab.charity.services;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(CategoryDto categoryDto) {
        categoryRepository.save(dtoToEntity(categoryDto));
    }

    public void updateCategory(CategoryDto categoryDto) {
        categoryRepository.save(dtoToEntity(categoryDto));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> getList(){
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categoryRepository.findAll()){
            categoryDtos.add(entityToDto(category));
        }
        return categoryDtos;
    }


    public CategoryDto entityToDto (Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category dtoToEntity (CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }
}
