package ru.korchinskiy.service;

import ru.korchinskiy.dto.CategoryDto;
import ru.korchinskiy.dto.CategoryTreeDto;
import ru.korchinskiy.dto.CategoryWithProductsDto;
import ru.korchinskiy.message.Message;

import java.util.List;

public interface CategoryService {
    /**
     * Returns category by id
     *
     * @param id Long
     * @return CategoryDto object
     */
    CategoryDto getCategoryById(Long id);

    /**
     * Returns category with list of products filtered by params
     *
     * @param id      Long category id
     * @param minCost Double
     * @param maxCost Double
     * @return CategoryWithProductsDto object
     */
    CategoryWithProductsDto getCategoryWithProductsByParams(Long id, Double minCost, Double maxCost, String year);

    /**
     * Returns List of subcategories by parent category id
     * if parent category doesn't have subcategories returns parent of parent subcategories
     *
     * @param id Long parent id
     * @return List<CategoryDto> list of CategoryDto objects
     */
    List<CategoryDto> getCategoriesByParentId(Long id);

    /**
     * Returns category tree form all categories
     * Every CategoryTreeDto can have subcategories of CategoryTreeDto
     *
     * @return List<CategoryTreeDto> list of CaegoryTreeDto objects
     */
    List<CategoryTreeDto> getCategoryTree();

    /**
     * Returns all existing categories
     *
     * @return List<CategoryDto> list of CaegoryDto objects
     */
    List<CategoryDto> getAllCategories();

    /**
     * Updates category entity with categoryDto data
     * returns confirm message if success or error message if fail
     *
     * @param categoryDto CategoryDto
     * @return Message object with 3 lists of confirms, warnings and errors
     */
    Message updateCategory(CategoryDto categoryDto);

    /**
     * Saves new category entity to database
     * returns confirm message if success or error message if fail
     *
     * @param categoryDto CategoryDto
     * @return Message object with 3 lists of confirms, warnings and errors
     */
    Message saveCategory(CategoryDto categoryDto);

    /**
     * Removes category from database
     *
     * @param categoryId Long
     * @return Message object with 3 lists of confirms, warnings and errors
     */
    Message removeCategory(Long categoryId);

    /**
     * Builds category tree from list of categories
     * Every CategoryTreeDto can have subcategories of CategoryTreeDto
     *
     * @param categoryTreeDtoList List<CategoryTreeDto>
     * @return List<CategoryTreeDto> list of categories
     */
    List<CategoryTreeDto> buildCategoryTree(List<CategoryTreeDto> categoryTreeDtoList);
}
