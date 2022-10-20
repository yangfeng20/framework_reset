package com.maple.note.format;

import com.alibaba.fastjson.JSONPath;

/**
 * @author 杨锋
 * @date 2022/10/20 20:11
 * desc:
 */

public class JsonPathDemo {
    public static void main(String[] args) {

        Object extract = JSONPath.extract(generate(), "$.userId");
        System.out.println(extract);
    }


    public static String generate() {
        return "[{\"userId\":1813616,\"name\":\"987654\",\"password\":\"987654\"},{\"userId\":1813614,\"name\":\"987654\",\"password\":\"987654\"},{\"userId\":1812872,\"name\":\"987654\",\"password\":\"987654\"},{\"userId\":1774989,\"name\":\"CS2108\",\"password\":\"cs2108\"},{\"userId\":1751924,\"name\":\"CS2108\",\"password\":\"cs2108\"},{\"userId\":1774442,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1774456,\"name\":\"CS2108\",\"password\":\"cs2108\"},{\"userId\":1774433,\"name\":\"程艳春\",\"password\":\"CCTV\"},{\"userId\":1774438,\"name\":\"宋文静\",\"password\":\"***303043\"},{\"userId\":1774471,\"name\":\"程艳春\",\"password\":\"CCTV\"},{\"userId\":1774472,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1774437,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1774441,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1774472,\"name\":\"宋美玲\",\"password\":\"123456\"},{\"userId\":1774439,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1780352,\"name\":\"张昌伟\",\"password\":\"zzccww\"},{\"userId\":1774435,\"name\":\"程艳春\",\"password\":\"CCTV\"},{\"userId\":1751873,\"name\":\"PUB\",\"password\":\"PUB\"},{\"userId\":1802888,\"name\":\"PUB\",\"password\":\"PUB\"},{\"userId\":1848431,\"name\":\"0063\",\"password\":\"123\"},{\"userId\":1848348,\"name\":\"AILA\",\"password\":\"890\"},{\"userId\":1873705,\"name\":\"0065\",\"password\":\"1023\"},{\"userId\":1876804,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1820123,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876809,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876808,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876807,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876806,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876805,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876803,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1876802,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1823042,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1823041,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1820127,\"name\":\"云单\",\"password\":\"1111\"},{\"userId\":1820126,\"name\":\"云单\",\"password\":\"1111\"}]";
    }
}
