/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ResultVo
 * Author:   xutong
 * Date:     2018-12-10 17:43
 * Description: 返回结果集合
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br> 
 * 〈返回结果集合〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {
    private Integer code;
    private String msg;
    private T data;

}