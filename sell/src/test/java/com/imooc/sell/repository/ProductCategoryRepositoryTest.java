package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void find() {
        repository.findAll().forEach(System.out::println);
    }
    @Test
    public void save(){
        repository.save(new ProductCategory(1,"123",2,new Date(),new Date()));
    }
    @Test
    public void delectAll(){
        repository.deleteAll();
    }
    @Test
    public void update(){
        Optional<ProductCategory> byId = repository.findById(1);
        byId.ifPresent(x->x.setCategoryType(113));
        byId.ifPresent(x->repository.saveAndFlush(x));
        System.out.println(repository.findById(1));
    }
}