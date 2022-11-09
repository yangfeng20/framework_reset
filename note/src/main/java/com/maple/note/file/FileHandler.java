package com.maple.note.file;

import java.io.File;

/**
 * @author 杨锋
 * @date 2022/11/9 11:48
 * desc:
 */

public class FileHandler {
    public static void main(String[] args) {
        File file = new File("E:\\DATA\\视频学习资料");
        deleteMacFile(file);
    }


    /**
     * 删除指定文件夹下的mac文件（以【.】开头的文件）
     */
    public static void deleteMacFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            for (File innerFile : files) {
                if (innerFile.isDirectory()) {
                    deleteMacFile(innerFile);
                } else {
                    deleteStartNameFile(innerFile);
                }
            }
        }

    }

    private static void deleteStartNameFile(File file) {
        // 不以【._】开头，不需要删除
        if (file.getName().startsWith("._") || "._.DS_Store".equals(file.getName()) || ".DS_Store".equals(file.getName())) {
            if (file.delete()) {
                System.out.println("文件： " + file.getName() + " 删除成功");
            }
        }


    }
}
