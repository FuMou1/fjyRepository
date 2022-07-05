package com.xunqi.gulimall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Created: with IntelliJ IDEA.
 * @author: fjy
 * @createTime: 2022-05-04 23:19
 **/

@Data
public class FareVo {

    private MemberAddressVo address;

    private BigDecimal fare;

}
