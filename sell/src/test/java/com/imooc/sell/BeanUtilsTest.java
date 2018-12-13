/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BeanUtilsTest
 * Author:   xutong
 * Date:     2018-12-11 09:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell;

import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanUtilsTest {
    @Autowired
    private ProductInfoService productInfoService;
    @Test
    public void test() {
        Optional<ProductInfo> byId = productInfoService.findById("1001");
   byId.ifPresent(System.out::println);

        byId.ifPresent(e -> {
            HashMap<String, String> source = new HashMap<>();
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(e.getClass());
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method readMethod = propertyDescriptor.getReadMethod();

                try {
                    System.out.println(propertyDescriptor.getName()+"======"+readMethod.invoke(e));
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            }
            BeanUtils.copyProperties(source,e);
            source.forEach((x,y)->System.out.println(x+"+++++++"+y));
        });

    }
}