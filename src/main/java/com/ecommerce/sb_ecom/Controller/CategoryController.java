package com.ecommerce.sb_ecom.Controller;

import com.ecommerce.sb_ecom.Config.AppConstants;
import com.ecommerce.sb_ecom.Payload.CategoryDTO;
import com.ecommerce.sb_ecom.Payload.CategoryResponse;
import com.ecommerce.sb_ecom.Service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    //@Autowired
    //use this if not want constructor for categoryService
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",required = false,defaultValue = AppConstants.PAGE_NUMBER)Integer pageNumber, @RequestParam(name = "pageSize",required = false,defaultValue = AppConstants.PAGE_SIZE)Integer pageSize,@RequestParam(name = "sortBy",required = false,defaultValue = AppConstants.SORT_BY)String sortBy,@RequestParam(name = "sortOrder",required = false,defaultValue = AppConstants.SORT_ORDER)String sortOrder) {
        CategoryResponse categories=categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/public/category")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO savedCategory=categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {
        CategoryDTO status=categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PutMapping("/public/category/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO status=categoryService.updateCategory(categoryId,categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
