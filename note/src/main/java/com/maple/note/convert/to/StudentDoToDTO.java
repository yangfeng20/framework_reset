package com.maple.note.convert.to;

import com.maple.note.convert.entity.StudentDO;
import com.maple.note.convert.entity.StudentDTO;
import com.maple.note.convert.entity.UserInfo;
import com.maple.note.convert.frame.AbsDataTransform;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 杨锋
 * @date 2022/11/26 9:20
 * desc:
 */

@ToString
public class StudentDoToDTO extends AbsDataTransform<StudentDTO, StudentDO> {


    private Map<Long, String> createUserMap = Collections.emptyMap();


    @Override
    public void executeBefore(List<StudentDO> sourceList) {
        createUserMap = sourceList.stream()
                .map(StudentDO::getCreateId)
                .collect(Collectors.toMap(Function.identity(), Object::toString, (x, y) -> y));
    }

    @Override
    public void executeIng(StudentDO source, StudentDTO result) {
        UserInfo userInfo = UserInfo.builder().id(source.getCreateId()).build();

        Optional.ofNullable(createUserMap.get(source.getCreateId()))
                .ifPresent(userInfo::setName);
        result.setCreateId(userInfo);
    }

    @Override
    public void executeAfter() {
        createUserMap.clear();
    }
}
