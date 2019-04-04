package ru.korchinskiy.service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.korchinskiy.config.WebConfig;
import ru.korchinskiy.dto.CategoryTreeDto;
import ru.korchinskiy.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class CategoryServiceImplTest {
    private static CategoryTreeDto categoryTreeDto1;
    private static CategoryTreeDto categoryTreeDto2;
    private static CategoryTreeDto categoryTreeDto3;
    private static CategoryTreeDto categoryTreeDto4;
    private static CategoryTreeDto categoryTreeDto5;
    private static List<CategoryTreeDto> categoryTreeDtoList;

    @Autowired
    private CategoryService categoryService;

    @BeforeClass
    public static void setUp() {
        categoryTreeDto1 = new CategoryTreeDto(1L, "title", 0L, new ArrayList<>());
        categoryTreeDto2 = new CategoryTreeDto(2L, "title", 0L, new ArrayList<>());
        categoryTreeDto3 = new CategoryTreeDto(3L, "title", 0L, new ArrayList<>());
        categoryTreeDto4 = new CategoryTreeDto(4L, "title", 0L, new ArrayList<>());
        categoryTreeDto5 = new CategoryTreeDto(5L, "title", 0L, new ArrayList<>());
        categoryTreeDtoList = new ArrayList<>();
        categoryTreeDtoList.add(categoryTreeDto1);
        categoryTreeDtoList.add(categoryTreeDto2);
        categoryTreeDtoList.add(categoryTreeDto3);
        categoryTreeDtoList.add(categoryTreeDto4);
        categoryTreeDtoList.add(categoryTreeDto5);
    }

    @Test
    public void buildCategoryTree1() {
        List<CategoryTreeDto> expectedCategoryTree = categoryService.buildCategoryTree(categoryTreeDtoList);
        List<CategoryTreeDto> categoryTree = categoryTreeDtoList;
        Assert.assertEquals(expectedCategoryTree, categoryTree);
    }

    @Test
    public void buildCategoryTree2() {
        categoryTreeDto3.setParentId(1L);
        categoryTreeDto4.setParentId(2L);
        List<CategoryTreeDto> expectedCategoryTree = categoryService.buildCategoryTree(categoryTreeDtoList);

        List<CategoryTreeDto> subCategory1 = new ArrayList<>();
        subCategory1.add(categoryTreeDto3);
        categoryTreeDto1.setSubcategories(subCategory1);
        List<CategoryTreeDto> subCategory2 = new ArrayList<>();
        subCategory2.add(categoryTreeDto4);
        categoryTreeDto2.setSubcategories(subCategory2);
        List<CategoryTreeDto> categoryTree = new ArrayList<>();
        categoryTree.add(categoryTreeDto1);
        categoryTree.add(categoryTreeDto2);
        categoryTree.add(categoryTreeDto5);

        Assert.assertEquals(expectedCategoryTree, categoryTree);
    }

    @Test
    public void buildCategoryTree3() {
        categoryTreeDto3.setParentId(1L);
        categoryTreeDto4.setParentId(2L);
        categoryTreeDto5.setParentId(3L);
        List<CategoryTreeDto> expectedCategoryTree = categoryService.buildCategoryTree(categoryTreeDtoList);

        List<CategoryTreeDto> subCategory1 = new ArrayList<>();
        subCategory1.add(categoryTreeDto3);
        categoryTreeDto1.setSubcategories(subCategory1);
        List<CategoryTreeDto> subCategory2 = new ArrayList<>();
        subCategory2.add(categoryTreeDto4);
        categoryTreeDto2.setSubcategories(subCategory2);
        List<CategoryTreeDto> subCategory3 = new ArrayList<>();
        subCategory3.add(categoryTreeDto5);
        categoryTreeDto3.setSubcategories(subCategory3);
        List<CategoryTreeDto> categoryTree = new ArrayList<>();
        categoryTree.add(categoryTreeDto1);
        categoryTree.add(categoryTreeDto2);

        Assert.assertEquals(expectedCategoryTree, categoryTree);
    }

    @Test
    public void buildCategoryTree4() {
        categoryTreeDto3.setParentId(1L);
        categoryTreeDto4.setParentId(3L);
        categoryTreeDto5.setParentId(3L);
        List<CategoryTreeDto> expectedCategoryTree = categoryService.buildCategoryTree(categoryTreeDtoList);

        List<CategoryTreeDto> subCategory1 = new ArrayList<>();
        subCategory1.add(categoryTreeDto3);
        categoryTreeDto1.setSubcategories(subCategory1);
        List<CategoryTreeDto> subCategory2 = new ArrayList<>();
        subCategory2.add(categoryTreeDto4);
        subCategory2.add(categoryTreeDto5);
        categoryTreeDto3.setSubcategories(subCategory2);
        List<CategoryTreeDto> categoryTree = new ArrayList<>();
        categoryTree.add(categoryTreeDto1);
        categoryTree.add(categoryTreeDto2);

        Assert.assertEquals(expectedCategoryTree, categoryTree);
    }
}
