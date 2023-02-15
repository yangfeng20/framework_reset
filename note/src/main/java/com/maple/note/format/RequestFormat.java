package com.maple.note.format;

/**
 * @author 杨锋
 * @date 2022/10/17 9:43
 * desc:
 */

public class RequestFormat {


    private static final String modelValue = "{\"formValues\":{\"attachmentList\":[],\"cargoJson\":{\"cargoType\":1},\"cargoTypeExtend\":\"{\\\"CargoTypeID\\\":1,\\\"CargoTypeName\\\":\\\"普货\\\",\\\"Children\\\":[]}\",\"consigneeJson\":{\"address\":\"\",\"customerReferenceNo\":\"123\",\"name\":\"\"},\"counterTerrorismJson\":{},\"docId\":1575894,\"documentParam\":{\"isVirtualChannel\":false,\"tags\":[3002]},\"isOpenCheck\":true,\"isSaveRuleVerificationDesc\":1,\"isSyncVgm\":false,\"lbContainerJson\":[],\"lineCarrierId\":15690,\"modifyProperties\":[\"bookingNo\",\"cargoJson\",\"cargoJson.cargoType.label\",\"counterTerrorismJson.cargoOfLoadingNo\",\"documentParam.cargoOwner\",\"cargoTypeExtend\",\"lineCarrierId\",\"consigneeJson\",\"containerCargoJson\",\"documentParam.customerBpId\",\"documentParam.customerBpName\",\"docId\",\"documentParam.docRoleId\",\"counterTerrorismJson.eightThousandCode\",\"formChanged\",\"groupId\",\"counterTerrorismJson.isNeedLoadOfbill\",\"isUploadFile\",\"lbComment\",\"lbContainerJson\",\"lbCopyOriginalNo\",\"lbOriginalNo\",\"lbOtherComment\",\"lbShowInfo\",\"lbVisaDate\",\"lbVisaTerminalId\",\"lineCustomsVoyage\",\"lineDischargingTerminalId\",\"lineEstimateArrPortDate\",\"lineEstimateDeliveryDate\",\"lineFromTerminalId\",\"lineReceiptPlaceTerminalId\",\"lineToTerminalId\",\"lineLbVessel\",\"mblno\",\"mdDocId\",\"nameAccountJson\",\"needConfirmUpdated\",\"notifyJson\",\"documentParam.operationId\",\"documentParam.externalOrderNo\",\"payerJson\",\"documentParam.relatedUserId\",\"documentParam.remark\",\"documentParam.saleId\",\"counterTerrorismJson.scacCode\",\"secondNotifyJson\",\"sendStatusArea\",\"documentParam.serviceId\",\"shipContractNo\",\"shipDeliveryModeId\",\"shipPaymentTerminalId\",\"shipPaymentWayId\",\"shipperJson\",\"shipShippingClauseId\",\"documentParam.tags\",\"transportInfo\",\"lineLbVoyage\",\"tags\",\"cargoJson\",\"documentParam\",\"rbSendChannelVO\",\"containerCargoJson\",\"containerJson\",\"mBlno\",\"lineFromTerminalId\",\"lineFromTerminal\",\"lineReceiptPlaceTerminalId\",\"lineReceiptPlaceTerminal\",\"lineDischargingTerminalId\",\"lineDischargingTerminal\",\"lineToTerminalId\",\"lineToTerminal\",\"counterTerrorismJson\",\"cargoTypeExtend\",\"taskId\",\"shipmentReferenceJson\",\"docChannel\",\"containerMapping\",\"packageMapping\",\"lineReceiptPlaceTerminalMapping\",\"lineFromTerminalMapping\",\"lineDischargingTerminalMapping\",\"lineToTerminalMapping\",\"configGroupId\",\"docChannelList\",\"configGroupIdList\",\"closingCustomDate\",\"billCloseDate\",\"vgmCloseDate\",\"openPortDate\",\"closePortDate\"],\"nameAccountJson\":{\"address\":\"\",\"aeoCode\":\"123\",\"cityName\":\"123\",\"countryCode\":\"CN\",\"countryId\":704,\"countryName\":\"CHINA\",\"customerReferenceNo\":\"\",\"provinceName\":\"\",\"zipCode\":\"1233\"},\"notifyJson\":{\"provinceName\":\"123\"},\"payerJson\":{\"code\":\"\",\"customerReferenceNo\":\"付款方PO\",\"exportRegistrarCode\":\"123\",\"importExportCode\":\"\",\"nvoccCertificateNo\":\"付款方NVOCC\",\"zipCode\":\"123\"},\"rbSendChannelVO\":{\"docId\":1575894,\"sendChannelVO\":{\"operationType\":1}},\"realConsignee\":{},\"realNotify\":{},\"realSecondNotify\":{},\"realShipper\":{\"provinceName\":\"123\"},\"secondNotifyJson\":{\"customerReferenceNo\":\"第二通知人PO\",\"nvoccCertificateNo\":\"第二通知人NVOCC\"},\"shipperJson\":{}},\"isAutoCheck\":true,\"validateFields\":[]}";


    public static void main(String[] args) {
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
                "xTraceId:\"335471ff295449a0a403d70eb7216d7a.111.16738376910898521\"\n" +
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
        System.out.println(headerValue);


        String request = "{\"header\":"+headerValue +",\"model\":"+modelValue+"}";
        System.out.println(request);

    }
}
