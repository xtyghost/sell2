/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SellException
 * Author:   xutong
 * Date:     2018-12-11 12:32
 * Description: 提供的异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.exception;

import com.imooc.sell.constants.ResultEnums;

/**
 * 〈一句话功能简述〉<br> 
 * 〈提供的异常〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
public class SellException extends RuntimeException {
    private Integer code;
    public SellException(ResultEnums resultEnums) {
        super(resultEnums.getMsg());
        code=resultEnums.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code=code;
    }
}