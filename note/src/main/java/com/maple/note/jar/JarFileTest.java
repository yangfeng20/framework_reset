package com.maple.note.jar;

import java.io.InputStream;
import java.net.URL;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author maple
 * Created Date: 2024/3/26 13:43
 * Description:
 */

public class JarFileTest {

    public static void main(String[] args) throws Exception {
        String jarFilePath = "file:/D:/Program%20Dev/Java/04-项目实战/smart_course/target/smart_course-0.0.1-SNAPSHOT.jar";
        String innerJarFilePath = "BOOT-INF/lib/smart-config-core-1.0-SNAPSHOT.jar";
        String filePathInsideInnerJar = "path/to/your/file.txt";

        URL url = new URL("jar:" + jarFilePath + "!/" + innerJarFilePath);
        String path = url.toURI().getPath();
        try (JarFile jarFile = new JarFile(url.getFile())) {
            ZipEntry entry = jarFile.getEntry(filePathInsideInnerJar);
            System.out.println(entry.getName());
            InputStream inputStream = jarFile.getInputStream(entry);
            // 这里可以根据需要读取 inputStream 中的内容
            // 例如，可以使用 BufferedReader 逐行读取文本文件内容
        }
    }
    public static void main1(String[] args) throws Exception {
        JarFile jarFile = new JarFile("jar:file:/D:/Program%20Dev/Java/04-项目实战/smart_course/target/smart_course-0.0.1-SNAPSHOT.jar!/BOOT-INF/lib/smart-config-core-1.0-SNAPSHOT.jar!/");


        jarFile.stream().forEach(jarEntry -> {
            System.out.println(jarEntry.getName());
        });

        jarFile.close();

    }
}
