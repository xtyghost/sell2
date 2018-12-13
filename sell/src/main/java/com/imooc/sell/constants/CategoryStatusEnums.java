/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CategoryStatusEnums
 * Author:   xutong
 * Date:     2018-12-10 16:29
 * Description: 商品状态的enum类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.constants;

/**
 * 〈一句话功能简述〉<br>
 * 〈商品状态的enum类〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
public enum CategoryStatusEnums {
    /**
     * 正常状态
     */
    ON(0, "正常"),
    /**
     * 下架状态
     */
    OFF(1, "下架");

    private int status;

    private String name;

    CategoryStatusEnums(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}