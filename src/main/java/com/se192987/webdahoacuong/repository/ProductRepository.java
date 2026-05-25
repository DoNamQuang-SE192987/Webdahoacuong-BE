package com.se192987.webdahoacuong.repository;

import com.se192987.webdahoacuong.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
