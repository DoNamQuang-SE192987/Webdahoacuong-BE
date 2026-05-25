package com.se192987.webdahoacuong.controller;

import com.se192987.webdahoacuong.dto.ApiResponse;
import com.se192987.webdahoacuong.entity.Product;
import com.se192987.webdahoacuong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("description") String description,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        
        Product product = productService.createProduct(name, price, description, image);
        return ResponseEntity.ok(new ApiResponse<>("Product created successfully", product));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse<>("Fetched all products", products));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("Product deleted successfully", null));
    }
}
