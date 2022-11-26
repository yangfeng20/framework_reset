package com.maple.note.convert.frame;

import com.maple.note.convert.entity.StudentDO;
import com.maple.note.convert.entity.StudentDTO;

import java.util.List;
import java.util.Objects;

/**
 * @author 杨锋
 * @date 2022/11/26 9:44
 * desc:
 */

public class ResultEqualsUtil {


    public static int equalsList(List<StudentDO> source, List<StudentDTO> result) {
        int count = 0;
        for (int i = 0; i < source.size(); i++) {
            if (equals(source.get(i), result.get(i))) {
                continue;
            }

            count++;
            //System.out.println("差异：" + source.get(i) + " " + result.get(i));
        }

        return count;
    }

    public static boolean equals(StudentDO source, StudentDTO result) {
        if (source == null || result == null) {
            return false;
        }

        return Objects.equals(source.getId(), result.getId()) &&
                Objects.equals(source.getAge(), result.getAge()) &&
                Objects.equals(source.getName(), result.getName()) &&
                Objects.equals(source.getCreateId(), result.getCreateId() == null ? -1 : result.getCreateId().getId())
                && result.getCreateId() == null ? false : result.getCreateId().getName() != null;
    }
}
