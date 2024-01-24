package com.maple.note.base.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yangfeng
 * @date : 2023/8/31 23:58
 * desc:
 */
@Data
public class Entity {

    @ExcelProperty(index = 0, value = "order_id")
    private Long orderId;

    @ExcelProperty(index = 1, value = "m_blno")
    private String mblno;

    @ExcelProperty(index = 2, value = "container_no")
    private String containerNo;

    @ExcelProperty(index = 3, value = "xxx")
    private String xxx;

    @ExcelProperty(index = 4, value = "actual_delivery_date")
    private Date actualDeliveryDate;
    @ExcelProperty(index = 5, value = "unload_time")
    private Date unloadTime;
    @ExcelProperty(index = 6, value = "realUnloadTime")
    private Date realUnloadTime;

    private Long id;


}
