package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void save(){
        OrderDetail s = new OrderDetail();
        s.setDetailId("32");
        s.setOrderId("1");
//        s.setCreateTime(new Date());
        s.setOrderId("qw");
        s.setProductId("12");
        s.setProductPrice(new BigDecimal(1));
        s.setProductQuantity(10);
//        s.setUpdateTime(new Date());
        s.setProductIcon("sdfs");
        s.setProductName("sdf");
        repository.save(s);
    }
    @Test
    public void delete(){
        repository.deleteAll();
    }
    @Test
    public void update(){
        Optional<OrderDetail> one = repository.findById("1");
        one.ifPresent(x->x.setProductName("sdfa117777f"));
        one.ifPresent(x->repository.save(x));

    }
    @Test
    public void select(){
        repository.findAll().forEach(System.out::println);
        Optional<OrderDetail> byId = repository.findById("1");
        byId.ifPresent(x->x.setProductQuantity(313));
        byId.ifPresent(x->repository.save(x));
     repository.findById("1").ifPresent(System.out::println);

    }
}