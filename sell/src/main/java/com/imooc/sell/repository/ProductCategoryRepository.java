package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {
}
