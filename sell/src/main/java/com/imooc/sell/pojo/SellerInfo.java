/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SellerInfo
 * Author:   xutong
 * Date:     2018-12-06 16:55
 * Description: 买家信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈买家信息〉
 *
 * @author xutong
 * @create 2018-12-06
 * @since 1.0.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class SellerInfo {
    @NotNull
    @Id
    private String id;
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String openid;

    private Date createTime;

    private Date updateTime;

}