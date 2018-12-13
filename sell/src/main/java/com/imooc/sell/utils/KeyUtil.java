/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: KeyUtil
 * Author:   xutong
 * Date:     2018-12-11 14:41
 * Description: 获取订单id的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.utils;

import java.time.Clock;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈获取订单id的工具类〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
public class KeyUtil {
    public static synchronized String getKey() {
        long millis = Clock.systemDefaultZone().millis();
        Random random = new Random();
        Long key = random.nextInt(9000000) + 1000000L;
        return String.valueOf(key + millis);
    }

}