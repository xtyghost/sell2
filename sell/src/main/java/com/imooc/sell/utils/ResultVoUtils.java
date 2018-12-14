/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ResultVoUtils
 * Author:   xutong
 * Date:     2018-12-11 09:00
 * Description: 获取结果对象的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.utils;

import com.imooc.sell.Vo.ResultVo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈获取结果对象的工具类〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class ResultVoUtils {
    public static ResultVo success(Object o){
        ResultVo vo = new ResultVo();
        vo.setCode(0);
        vo.setMsg("成功");
        vo.setData(o);
        return vo;
    }
    public static ResultVo error(Object o, Integer code, String msg){

        return new ResultVo(code,msg,o);
    }
   public static ResultVo success(){
      return   success(null);
   }
}