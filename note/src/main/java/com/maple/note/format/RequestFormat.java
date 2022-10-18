package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {
    public static void main(String[] args) {
        String str = "accountId:1676869\n" +
                "accountTenantId:1\n" +
                "xAppId:\"40062\"\n" +
                "xBPId:\"1\"\n" +
                "xClientIp:\"219.134.134.102, 47.111.193.47, 112.124.159.199, 100.97.241.57, 192.168.210.238\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"aac6e3e37b264c512eecbd9f9f1c4343\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:1\n" +
                "xReadTimeoutMillis:0\n" +
                "xSession:\"2e6cad008500aa9871344f56f2f7e194\"\n" +
                "xSourceAppId:\"62010\"\n" +
                "xTenantId:1\n" +
                "xTraceId:\"d57efad29b504ea79a5934b0e644e31c.18870.16659709158476445\"\n" +
                "xUserId:1753685\n" +
                "xUserName:\"蔡素琼\"";

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
