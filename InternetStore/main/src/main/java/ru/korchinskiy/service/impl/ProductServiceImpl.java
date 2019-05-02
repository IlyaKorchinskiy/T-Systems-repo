package ru.korchinskiy.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.korchinskiy.dao.CategoryDAO;
import ru.korchinskiy.dao.ProductDAO;
import ru.korchinskiy.dto.NewProductDto;
import ru.korchinskiy.dto.ProductDto;
import ru.korchinskiy.dto.ProductWithCategoriesDto;
import ru.korchinskiy.entity.Category;
import ru.korchinskiy.entity.Product;
import ru.korchinskiy.message.Message;
import ru.korchinskiy.service.DTOMappingService;
import ru.korchinskiy.service.ImageService;
import ru.korchinskiy.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    private ProductDAO productDAO;
    private DTOMappingService dtoMappingService;
    private CategoryDAO categoryDAO;
    private ImageService imageService;

    @Override
    @Transactional
    public ProductWithCategoriesDto getProductById(Long id) {
        Product product = productDAO.getProductById(id);
        return dtoMappingService.convertToProductWithCategoriesDto(product);
    }

    @Override
    @Transactional
    public List<ProductDto> getAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        return dtoMappingService.convertToProductDtoList(products);
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        List<Product> products = productDAO.getProductsByCategory(categoryId);
        return dtoMappingService.convertToProductDtoList(products);
    }

    @Override
    @Transactional
    public List<ProductDto> findProductsBySearch(String search) {
        Set<Product> products = new HashSet<>(productDAO.findProductsBySearch(search));
        String[] searchWords = search.split("\\s");
        if (searchWords.length != 1) {
            for (String word : searchWords) {
                products.addAll(productDAO.findProductsBySearch(word));
            }
        }
        return dtoMappingService.convertToProductDtoList(new ArrayList<>(products));
    }

    @Override
    @Transactional
    public List<Integer> getProductYears() {
        return productDAO.getProductYears();
    }

    @Override
    @Transactional
    public Message saveProduct(NewProductDto productDto, HttpServletRequest request) {
        Message message = new Message();
        Product product = productDAO.getProductByTitle(productDto.getTitle());
        if (product != null) {
            message.getErrors().add(Message.PRODUCT_ALREADY_EXISTS);
            logger.info(Message.PRODUCT_ALREADY_EXISTS);
            return message;
        }
        product = dtoMappingService.convertToProduct(productDto);
        try {
            product.setPhotoMd(imageService.saveFile(productDto.getPhotoMd()));
            product.setPhotoSm(imageService.saveFile(productDto.getPhotoSm()));
        } catch (IOException ex) {
            message.getErrors().add(Message.FILE_SAVE_FAIL);
            logger.error(Message.FILE_SAVE_FAIL, ex);
            return message;
        }
        productDAO.saveProduct(product);
        List<Category> categories = new ArrayList<>();
        for (Long categoryId : productDto.getCategories()) {
            categories.add(categoryDAO.getCategoryById(categoryId));
        }
        product.setCategories(categories);
        message.getConfirms().add(Message.PRODUCT_ADD_SUCCESS);
        logger.info(Message.PRODUCT_ADD_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductTitle(String title, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductByTitle(title);
        if (product != null) {
            message.getErrors().add(Message.PRODUCT_ALREADY_EXISTS);
            logger.info(Message.PRODUCT_ALREADY_EXISTS);
            return message;
        }
        product = productDAO.getProductById(productId);
        product.setTitle(title);
        message.getConfirms().add(Message.PRODUCT_UPDATE_TITLE_SUCCESS);
        logger.info(Message.PRODUCT_UPDATE_TITLE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductAuthor(String author, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        product.setAuthor(author);
        message.getConfirms().add(Message.PRODUCT_UPDATE_AUTHOR_SUCCESS);
        logger.info(Message.PRODUCT_UPDATE_AUTHOR_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductYear(Integer year, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        product.setYear(year.toString());
        message.getConfirms().add(Message.PRODUCT_UPDATE_YEAR_SUCCESS);
        logger.info(Message.PRODUCT_UPDATE_YEAR_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductDescription(String description, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        product.setDescription(description);
        message.getConfirms().add(Message.PRODUCT_UPDATE_DESCRIPTION_SUCCESS);
        logger.info(Message.PRODUCT_UPDATE_DESCRIPTION_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductCategories(List<Long> ids, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        List<Category> categories = new ArrayList<>();
        for (Long id : ids) {
            categories.add(categoryDAO.getCategoryById(id));
        }
        product.setCategories(categories);
        message.getConfirms().add(Message.PRODUCT_UPDATE_CATEGORIES_SUCCESS);
        logger.info(Message.PRODUCT_UPDATE_CATEGORIES_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductPhotoMd(MultipartFile photoMd, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        try {
            product.setPhotoMd(imageService.saveFile(photoMd));
        } catch (IOException ex) {
            message.getErrors().add(Message.FILE_SAVE_FAIL);
            logger.error(Message.FILE_SAVE_FAIL, ex);
            return message;
        }
        message.getConfirms().add(Message.FILE_SAVE_SUCCESS);
        logger.info(Message.FILE_SAVE_SUCCESS);
        return message;
    }

    @Override
    @Transactional
    public Message updateProductPhotoSm(MultipartFile photoSm, Long productId) {
        Message message = new Message();
        Product product = productDAO.getProductById(productId);
        try {
            product.setPhotoSm(imageService.saveFile(photoSm));
        } catch (IOException ex) {
            message.getErrors().add(Message.FILE_SAVE_FAIL);
            logger.error(Message.FILE_SAVE_FAIL, ex);
            return message;
        }
        message.getConfirms().add(Message.FILE_SAVE_SUCCESS);
        logger.info(Message.FILE_SAVE_SUCCESS);
        return message;
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setDtoMappingService(DTOMappingService dtoMappingService) {
        this.dtoMappingService = dtoMappingService;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Autowired
    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }
}
