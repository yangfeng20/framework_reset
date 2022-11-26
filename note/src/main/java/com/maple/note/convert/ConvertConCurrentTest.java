package com.maple.note.convert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.StopWatch;
import com.maple.note.convert.entity.StudentDO;
import com.maple.note.convert.entity.StudentDTO;
import com.maple.note.convert.frame.ResultEqualsUtil;
import com.maple.note.convert.to.StudentDoToDTO;
import org.apache.commons.math3.util.Pair;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 杨锋
 * @date 2022/11/26 9:19
 * desc:
 */

public class ConvertConCurrentTest {
    public static void main(String[] args) throws Exception {

        // 构建源
        List<List<StudentDO>> sourceDataList = generateSource(1000);

        // 构建转换对象
        StudentDoToDTO studentDoToDTO1 = new StudentDoToDTO();
        StudentDoToDTO studentDoToDTO2 = new StudentDoToDTO();

        StopWatch stopWatch = new StopWatch("转换耗时对比");

        // 批量转换并比较
        stopWatch.start();
        baseConvert(studentDoToDTO1, sourceDataList);
        stopWatch.stop();

        // 并发转换比较
        stopWatch.start();
        conCurrentConvert(studentDoToDTO2, sourceDataList);
        stopWatch.stop();

        // 并发多例转换比较
        stopWatch.start();
        conCurrentConvertPrototype(sourceDataList);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        System.exit(0);
    }


    /**
     * 基本转换，模拟一个请求接着一个请求，没有并发
     *
     * @param studentDoToDTO 学生做dto
     * @param source         源
     */
    private static void baseConvert(StudentDoToDTO studentDoToDTO, List<List<StudentDO>> source) {
        System.out.println("开始普通比较：");
        List<Pair<List<StudentDO>, List<StudentDTO>>> resultMapper = new ArrayList<>();

        for (int i = 0; i < source.size(); i++) {
            List<StudentDO> sourceList = source.get(i);
            List<StudentDTO> studentDTOList = studentDoToDTO.transformList(sourceList);
            resultMapper.add(Pair.create(sourceList, studentDTOList));
        }

        // 比较结果
        resultEquals(resultMapper);
    }


    /**
     * 并发转换，模拟并发场景
     *
     * @param studentDoToDTO 学生做dto
     * @param source         学生dolist
     * @return {@link List}<{@link StudentDTO}>
     */
    private static void conCurrentConvert(StudentDoToDTO studentDoToDTO, List<List<StudentDO>> source) throws Exception {
        System.out.println("开始并发比较：");
        List<Pair<List<StudentDO>, List<StudentDTO>>> resultMapper = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < source.size(); i++) {
            int finalI = i;
            List<StudentDO> studentDOList = source.get(finalI);
            Callable<List<StudentDTO>> result = () -> studentDoToDTO.transformList(studentDOList);
            threadPool.submit(result);
            resultMapper.add(Pair.create(studentDOList, result.call()));
        }
        threadPool.shutdown();

        // 比较结果
        resultEquals(resultMapper);
    }



    /**
     * 并发转换，模拟并发场景(多例对象)
     *
     * @param studentDoToDTO 学生做dto
     * @param source         学生dolist
     * @return {@link List}<{@link StudentDTO}>
     */
    private static void conCurrentConvertPrototype(List<List<StudentDO>> source) throws Exception {
        System.out.println("开始并发比较：");
        List<Pair<List<StudentDO>, List<StudentDTO>>> resultMapper = new ArrayList<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0; i < source.size(); i++) {
            int finalI = i;
            List<StudentDO> studentDOList = source.get(finalI);
            Callable<List<StudentDTO>> result = () -> new StudentDoToDTO().transformList(studentDOList);
            threadPool.submit(result);
            resultMapper.add(Pair.create(studentDOList, result.call()));
        }
        threadPool.shutdown();

        // 比较结果
        resultEquals(resultMapper);
    }

    private static void resultEquals(List<Pair<List<StudentDO>, List<StudentDTO>>> resultMapper) {
        StudentDO studentDO = resultMapper.get(resultMapper.size() - 1).getKey().get(0);
        StudentDTO studentDTO = resultMapper.get(resultMapper.size() - 1).getValue().get(0);
        System.out.println("批量转换结束，抽样结果：" + studentDO + " " + studentDTO);
        System.out.println("开始比较, 之后没有输出代表转换正常\n");


        int total = 0;
        int outerCount = 0;

        // 获取结果比较
        for (int i = 0; i < resultMapper.size(); i++) {
            Pair<List<StudentDO>, List<StudentDTO>> pair = resultMapper.get(i);
            List<StudentDO> studentDOList = pair.getKey();
            List<StudentDTO> studentDTOList = pair.getValue();
            if (total == 0) {
                total = resultMapper.size() * studentDOList.size();
            }
            int innerCount = ResultEqualsUtil.equalsList(studentDOList, studentDTOList);
            outerCount = innerCount == 0 ? outerCount : innerCount+outerCount;
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        System.out.println("对比total：" + total + " 差异次数：" + outerCount + " 错误率："+ percentInstance.format((double)outerCount/total));
    }


    private static List<List<StudentDO>> generateSource(int size) {
        // 生成一个源模版list
        Random random = new Random();
        List<StudentDO> studentDOList = Stream.generate(() -> StudentDO.builder()
                        .name(random.nextInt(10000) + "")
                        .age(random.nextInt(10000))
                        .id((long) random.nextInt(10000))
                        .createId((long) random.nextInt(10000))
                        .build()
                // 每次请求有【limit】条数据需要批量转换
        ).limit(100).collect(Collectors.toList());


        ArrayList<List<StudentDO>> result = new ArrayList<>(size);
        // 拷贝 【一次来（siz）个请求】
        for (int j = 0; j < size; j++) {
            List<StudentDO> studentDTOList = Stream.iterate(0, i -> ++i)
                    .map(index -> BeanUtil.copyProperties(studentDOList.get(index), StudentDO.class))
                    .limit(studentDOList.size())
                    .collect(Collectors.toList());

            result.add(studentDTOList);
        }
        return result;
    }
}
