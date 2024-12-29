package com.ecommerce.sb_ecom.Service;

import com.ecommerce.sb_ecom.Exceptions.ApiException;
import com.ecommerce.sb_ecom.Exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecom.Model.Category;
import com.ecommerce.sb_ecom.Payload.CategoryDTO;
import com.ecommerce.sb_ecom.Payload.CategoryResponse;
import com.ecommerce.sb_ecom.Repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortBy,String sortOrder) {
        Sort sortByOrder = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sortByOrder);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories=categoryPage.getContent();
        if(categories.isEmpty()){
            throw new ApiException("No Categories");
        }
        List<CategoryDTO> categoryDTOS=categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
        CategoryResponse categoryResponse=new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setLastPage(categoryPage.isLast());
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDb=categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!=null) {
            throw new ApiException("Category with this name already exists "+category.getCategoryName());
        }
        Category savedCategory1=categoryRepository.save(category);
        return modelMapper.map(savedCategory1, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category savedCategory=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepository.delete(savedCategory);
        return modelMapper.map( savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category savedCategoryFromDB=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","categoryId",categoryId));
        savedCategoryFromDB.setCategoryId(categoryId);
        Category savedCategory =categoryRepository.save(savedCategoryFromDB);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }
}
