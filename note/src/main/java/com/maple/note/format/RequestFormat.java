package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {


    private static final

    String MODEL_VALUE = "{\"page\":1,\"size\":20,\"sort\":[],\"taskType\":211}";


    public static void main(String[] args) {
        // 现在的model是江苏远洋租户，并且为江苏远洋管理员
        String str = "accountId:1\n" +
                "accountTenantId:3453540\n" +
                "ext:\n" +
                "instanceId:2\n" +
                "xBPId:\"3458371\"\n" +
                "xClientIp:\"106.15.37.67\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"5673d64bca2865dacd1313ffc5f456e5\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSourceAppId:\"61503\"\n" +
                "xTenantId:3454329\n" +
                "xUserId:3469467\n" +
                "xUserName:\"江苏远洋管理\"";

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

        String headerValue = strBuilder.toString();
        System.out.println("header数据：");
        System.out.println(headerValue);

        System.out.println("\nrequest数据：");
        String request = "{\"header\":"+headerValue +",\"model\":"+MODEL_VALUE+"}";
        System.out.println(request);

    }
}
