package com.se192987.webdahoacuong.service;

import com.se192987.webdahoacuong.entity.Product;
import com.se192987.webdahoacuong.exception.ResourceNotFoundException;
import com.se192987.webdahoacuong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public Product createProduct(String name, String price, String description, MultipartFile image) {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            imageUrl = fileStorageService.storeFile(image);
        }

        Product product = Product.builder()
                .name(name)
                .price(price)
                .description(description)
                .imageUrl(imageUrl)
                .build();
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepository.delete(product);
    }
}
