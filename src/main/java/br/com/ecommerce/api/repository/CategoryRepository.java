package br.com.ecommerce.api.repository;

import br.com.ecommerce.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
