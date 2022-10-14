package com.maple.mybatis.param;

import com.maple.mybatis.mvc.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * @author 杨锋
 * @date 2022/10/14 17:06
 * desc:
 */

public interface AccountParamMapper {

    Account selectList(String name);

    int insert(Account account);

    Account selectByName( String name, Long id);

    List<Map<String, Object>> selectMap();


}
