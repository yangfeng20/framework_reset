package com.maple.note.format;

import cn.hutool.core.bean.BeanUtil;
import com.maple.note.entity.Address01;
import com.maple.note.entity.Student01;
import com.maple.note.entity.Student02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 杨锋
 * @date 2022/10/18 15:59
 * desc:
 */

public class CopyTest {
    public static void main(String[] args) {
        Student01 student01 = new Student01();
        student01.setName("student01");


        List<Address01> addressList = Stream.iterate(0, i -> i + 1).map(index -> {
            Address01 address = new Address01();
            address.setDesc("address" + index);
            return address;
        }).limit(10).collect(Collectors.toList());

        student01.setAddressList(addressList);

        Student02 student02 = BeanUtil.copyProperties(student01, Student02.class);


        System.out.println("student01 = " + student01);
        System.out.println("student02 = " + student02);
    }
}
