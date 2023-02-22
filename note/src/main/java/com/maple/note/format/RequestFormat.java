package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {


    private static final

    String MODEL_VALUE = "{\"properties\":[\"orderRemark\",\"isSyncBooking\",\"file-list\",\"lineCarrierId\",\"lineEstimateDeliveryDate\",\"mblno\",\"bookingNo\",\"lineReceiptPlaceTerminalId\",\"lineFromTerminalId\",\"lineDischargingTerminalId\",\"lineToTerminalId\",\"lineViaTerminalCode\",\"containerMapping\",\"containerJson\",\"lineFromDockId\",\"lineLineCode\",\"vesselAliasName\",\"carrierVesselNames\",\"lineVessel\",\"vesselAliasCode\",\"lineVesselId\",\"lineCustomsVoyage\",\"lineVoyage\",\"soNo\",\"lineBargeVessel\",\"lineBargeVoyage\",\"lineBargeEstimateDeliveryDate\",\"containerStorageYardId\",\"suitcaseContact\",\"suitcaseContactInformation\",\"shipAgentId\",\"contractNo\",\"poNo\",\"infoShippingConfirmComments\",\"infoClosingCustomDate\",\"infoBillCloseDate\",\"infoVgmCloseDate\",\"openPortDate\",\"closePortDate\",\"modifyProperties\",\"docOperateType\",\"isSaveRuleVerificationDesc\",\"docId\",\"documentParam\",\"tags\",\"isSyncBooking\"],\"reqModel\":{\"bookingNo\":\"1111QQQQ\",\"carrierVesselNames\":\"VITA N\",\"closePortDate\":1676926800000,\"containerJson\":[{\"boxIdentification\":102,\"containerType\":\"20GP\",\"containerTypeId\":1,\"isNOR\":false,\"isSoc\":false,\"quantity\":1}],\"contractNo\":\"123123\",\"docId\":1576957,\"documentParam\":{\"id\":1576957,\"isSyncBooking\":0,\"isVirtualChannel\":false,\"tags\":[]},\"infoBillCloseDate\":1677099900000,\"infoClosingCustomDate\":1677182640000,\"infoShippingConfirmComments\":\"123123\",\"infoVgmCloseDate\":1676746800000,\"isBatchCreate\":false,\"isSaveRuleVerificationDesc\":1,\"isSyncBooking\":\"0\",\"lineBargeEstimateDeliveryDate\":1676131200000,\"lineBargeVessel\":\"123\",\"lineBargeVoyage\":\"1231212\",\"lineCarrierId\":4559,\"lineCustomsVoyage\":\"12311\",\"lineDischargingTerminalId\":30066,\"lineEstimateDeliveryDate\":1675958640000,\"lineFromTerminalId\":1009894,\"lineLineCode\":\"123\",\"lineReceiptPlaceTerminalId\":1009894,\"lineToTerminalId\":30066,\"lineVessel\":\"MAERSK VALPARAISO\",\"lineVesselId\":5001290,\"lineViaTerminalCode\":\"2312\",\"lineVoyage\":\"3123\",\"mBlno\":\"1111QQQQ\",\"openPortDate\":1676955600000,\"poNo\":\"123123\",\"shipAgentId\":58362,\"soNo\":\"123\",\"suitcaseContact\":\"123\",\"suitcaseContactInformation\":\"123\",\"vesselAliasCode\":\"9433054\",\"vesselAliasName\":\"VITA N\"}}";


    public static void main(String[] args) {
        // 现在的model是江苏远洋租户，并且为江苏远洋管理员
        String str = "accountId:1\n" +
                "accountTenantId:3453540\n" +
                "ext:\n" +
                "xBPId:\"3458371\"\n" +
                "xClientIp:\"106.15.37.67\"\n" +
                "xConnectTimeoutMillis:0\n" +
                "xDeviceId:\"5673d64bca2865dacd1313ffc5f456e5\"\n" +
                "xLangCode:\"zh\"\n" +
                "xPlatformId:4\n" +
                "xReadTimeoutMillis:0\n" +
                "xSourceAppId:\"61501\"\n" +
                "xTenantId:3454329\n" +
                "xTraceId:\"eba336bb7f734f2094db11e21619e6bd.128.16766412132986865\"\n" +
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
