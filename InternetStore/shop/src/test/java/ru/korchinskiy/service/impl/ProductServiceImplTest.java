package ru.korchinskiy.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.NewProductDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.ImageService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    private NewProductDto newProductDto;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private CategoryDAO categoryDAO;

    @Mock
    private ImageService imageService;

    @Mock
    private DTOMappingService dtoMappingService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void setUp() throws Exception {
        newProductDto = new NewProductDto(1L, "title");
    }

    @Test
    public void saveProduct() throws IOException {
        List<Long> categories = Arrays.asList(1L);
        when(productDAO.getProductByTitle(newProductDto.getTitle())).thenReturn(null);
        when(imageService.saveFile(newProductDto.getPhotoMd())).thenReturn("path1.jpg");
        when(imageService.saveFile(newProductDto.getPhotoSm())).thenReturn("path2.jpg");
        when(dtoMappingService.convertToProduct(newProductDto)).thenReturn(new Product(1L, "title"));
        Message actual = productService.saveProduct(newProductDto);
        Assert.assertEquals(Message.PRODUCT_ADD_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductTitle() {
        when(productDAO.getProductByTitle("newTitle")).thenReturn(null);
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        Message actual = productService.updateProductTitle("newTitle", 1L);
        Assert.assertEquals(Message.PRODUCT_UPDATE_TITLE_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductAuthor() {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        Message actual = productService.updateProductAuthor("author", 1L);
        Assert.assertEquals(Message.PRODUCT_UPDATE_AUTHOR_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductYear() {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        Message actual = productService.updateProductYear(2005, 1L);
        Assert.assertEquals(Message.PRODUCT_UPDATE_YEAR_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductDescription() {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        Message actual = productService.updateProductDescription("description", 1L);
        Assert.assertEquals(Message.PRODUCT_UPDATE_DESCRIPTION_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductCategories() {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        when(categoryDAO.getCategoryById(1L)).thenReturn(new Category(1L, "title1"));
        when(categoryDAO.getCategoryById(2L)).thenReturn(new Category(2L, "title2"));
        List<Long> categories = Arrays.asList(1L, 2L);
        Message actual = productService.updateProductCategories(categories, 1L);
        Assert.assertEquals(Message.PRODUCT_UPDATE_CATEGORIES_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductPhotoMd() throws IOException {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        when(imageService.saveFile(null)).thenReturn("path.jpg");
        Message actual = productService.updateProductPhotoMd(null, 1L);
        Assert.assertEquals(Message.FILE_SAVE_SUCCESS, actual.getConfirms().get(0));
    }

    @Test
    public void updateProductPhotoSm() throws IOException {
        when(productDAO.getProductById(1L)).thenReturn(new Product(1L, "title"));
        when(imageService.saveFile(null)).thenReturn("path.jpg");
        Message actual = productService.updateProductPhotoSm(null, 1L);
        Assert.assertEquals(Message.FILE_SAVE_SUCCESS, actual.getConfirms().get(0));
    }
}
