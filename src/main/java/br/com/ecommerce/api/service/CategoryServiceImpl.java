package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Category;
import br.com.ecommerce.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

//    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria n encontrada"));

        categoryRepository.delete(category);
        return "categoria deletada";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Optional<Category> optionalCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst();

        if(optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingCategory);

            return savedCategory;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "categoria nao encontrada, insira um id correto");
        }
    }
}
