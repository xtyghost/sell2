/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Date2LongSerialzer
 * Author:   xutong
 * Date:     2018-12-14 11:04
 * Description: 将时间显示转换为long的转换器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈将时间显示转换为long的转换器〉
 * @use在指定字段上 @JsonSerialize(using=Date2LongSerialzer.class)
 *
 * @author xutong
 * @create 2018-12-14
 * @since 1.0.0
 */
public class Date2LongSerialzer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeNumber(date.getTime()/1000);
    }
}