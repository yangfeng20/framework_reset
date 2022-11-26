package com.maple.note.convert.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author 杨锋
 * @date 2022/11/26 9:23
 * desc:
 */

@Data
@Builder
public class UserInfo {

    private Long id;

    private String name;
}
