package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {
    public static void main(String[] args) {
        String str = "accountId:1\n" +
                "accountTenantId:3453540\n" +
                "ext:\n" +
                "xAppId:\"37002\"\n" +
                "xBPId:\"3458371\"\n" +
                "xClientIp:\"106.15.37.67, 10.88.105.132, 10.88.104.202, 10.88.104.175\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"062a351bd2cab69d62d6a829f16893a7\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSession:\"a37796331534cd4a2857adfe26d713ee\"\n" +
                "xSourceAppId:\"61501\"\n" +
                "xTenantId:3454329\n" +
                "xTraceId:\"ca1aac90ec3641dd8aa5290d40ba7c0e.49540.16709852050013533\"\n" +
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

        System.out.println(strBuilder.toString());

    }
}
