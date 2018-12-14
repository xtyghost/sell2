/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: LoggerTest
 * Author:   xutong
 * Date:     2018/11/12 4:36 PM
 * Description: 日志
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 〈一句话功能简述〉<br> 
 * 〈日志〉
 *
 * @author xutong
 * @create 2018/11/12
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    @Test
    public void test1(){
        String name="imooc";
        String password ="123456";
        log.debug("debug... ");
        log.info("name:" +name+"password:"+password);
        log.info("name:{}password:{}",name,password);
        log.error("error...");
    }

}