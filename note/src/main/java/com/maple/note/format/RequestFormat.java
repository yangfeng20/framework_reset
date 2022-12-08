package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {
    public static void main(String[] args) {
        String str = "accountId:3478157\n" +
                "accountTenantId:3453540\n" +
                "ext:\n" +
                "xAppId:\"41008\"\n" +
                "xBPId:\"0\"\n" +
                "xClientIp:\"106.15.37.67, 10.244.9.55\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"49ac722318d21753ad7fddedafdbd258\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSession:\"aa1a7b41df6bf4e567612925c75e9914\"\n" +
                "xSourceAppId:\"62501\"\n" +
                "xTenantId:1\n" +
                "xTraceId:\"2cbd92a1e43244e6b54635b4d05abdfb.121.16699456057733893\"\n" +
                "xUserId:3466878\n" +
                "xUserName:\"货代云管理员\"";

        format(str);
    }


    public static void format(String str) {
        StringBuilder strBuilder = new StringBuilder();
        if ("".equals(str)) {
            return;
        }

        boolean isColon = true;
        strBuilder.append("{");
        while (true) {

            if ("".equals(str)) {
                break;
            }

            if (isColon) {
                int colonIndex = str.indexOf(":");
                strBuilder.append("\"");
                String key = str.substring(0, colonIndex).trim();
                strBuilder.append(key);
                strBuilder.append("\":");
                isColon = false;

                str = str.substring(colonIndex + 1);
            } else {

                int lineFeedIndex = str.indexOf("\n");
                if (lineFeedIndex == -1) {
                    strBuilder.append(str);
                    break;
                }
                String value = str.substring(0, lineFeedIndex).trim();
                if ("".equals(value)) {
                    strBuilder.append("{}").append(",");
                } else {
                    strBuilder.append(value).append(",");
                }
                isColon = true;
                str = str.substring(lineFeedIndex + 1);
            }
        }
        strBuilder.append("}");

        System.out.println(strBuilder.toString());

    }
}
