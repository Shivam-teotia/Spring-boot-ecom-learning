package com.ecommerce.sb_ecom.Repository;

import com.ecommerce.sb_ecom.Model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(@NotBlank @Size(min = 1, max = 50,message = "must have more than 1 and less than 50 character") String categoryName);
}
