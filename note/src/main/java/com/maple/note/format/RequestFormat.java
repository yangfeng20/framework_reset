package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {
    public static void main(String[] args) {
        String str = "accountId:3477164\n" +
                "accountTenantId:3453540\n" +
                "ext:\n" +
                "xBPId:\"3458371\"\n" +
                "xClientIp:\"113.57.21.204\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"0131c1e51cabd95c2404a015d2c8774a\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSession:\"4bc19054d45c4d4af878da4c299f424b\"\n" +
                "xSourceAppId:\"61501\"\n" +
                "xTenantId:3454329\n" +
                "xTraceId:\"ed7d97f8c689402cac9ca9efbdf3175a.147.16661808644022965\"\n" +
                "xUserId:3483648\n" +
                "xUserName:\"罗娟\"";

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
