package com.maple.note.base.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yangfeng
 * @date : 2023/9/1 0:10
 * desc:
 */

@Data
public class ContainerMapEntity {

    @ExcelProperty(index = 0, value = "id")
    private Long id;

    @ExcelProperty(index = 1, value = "order_id")
    private Long orderId;

    @ExcelProperty(index = 2, value = "container_no")
    private String containerNo;

    @ExcelProperty(index = 3, value = "unload_time")
    private Date unloadTime;
}
