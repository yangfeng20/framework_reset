package com.maple.note.sftp;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author maple
 * Created Date: 2024/1/23 12:00
 * Description:
 */

@Slf4j
public class SftpMain {
    public static void main(String[] args) throws Exception{
        Sftp sftp = JschUtil.createSftp("120.133.26.135", 8822, "baiying", "baiying#202401");
        buildAndIntoTargetPath(sftp, new Date());
    }


    private static void buildAndIntoTargetPath(Sftp sftp, Date date) {
        // 目标sftp服务前缀路径
        String msxjSftpPath = "/temp/baiying/test/";
        List<String> pathList = new ArrayList<>(Arrays.asList(msxjSftpPath.split("/")));

        // 每天的数据目录
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String ytdDir = simpleDateFormat.format(Optional.ofNullable(date).orElse(new Date()));
        pathList.add(ytdDir);

        String targetPathDir = recursionMkdir(pathList, sftp);
        sftp.cd(targetPathDir);
    }


    private static String recursionMkdir(List<String> pathList, Sftp sftp) {
        StringJoiner joiner = new StringJoiner("/", "/", "");
        for (String path : pathList) {
            if (StringUtils.isBlank(path)) {
                continue;
            }
            joiner.add(path);
            if (!sftp.exist(joiner.toString())) {
                sftp.mkdir(joiner.toString());
            }
        }

        return joiner.toString();
    }

}
