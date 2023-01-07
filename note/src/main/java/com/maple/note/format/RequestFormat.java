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
                "xBPId:\"3458371\"\n" +
                "xClientIp:\"106.15.37.67\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"6fb8af9dd1d4678349b90950cbc08d7b\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSourceAppId:\"61501\"\n" +
                "xTenantId:3454329\n" +
                "xTraceId:\"19e7a3e2bb39408694e7aebfd5bccb85.127.16730036621623561\"\n" +
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
