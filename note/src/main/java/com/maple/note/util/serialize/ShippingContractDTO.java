package com.maple.note.util.serialize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gina
 * @description
 * @since 2021-07-12 17:54
 */
@Data
@NoArgsConstructor
public class ShippingContractDTO {


    @ApiModelProperty(value = "联系人代码前缀（USCI、OS...）")
    private String preCode;

    @ApiModelProperty(value = "代码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String chineseName;

    @ApiModelProperty(value = "国家代码")
    private String countryCode;

    @ApiModelProperty(value = "国家名")
    private String countryName;

    @ApiModelProperty(value = "国家id")
    private Long countryId;

    @ApiModelProperty(value = "省/州 名称")
    private String provinceName;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String telephone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "AEO企业编码")
    private String aeoCode;

    @ApiModelProperty(value = "邮编")
    private String zipCode;

    @ApiModelProperty(value = "船公司代码")
    private String carrierCode;

    @ApiModelProperty(value = "客户参考号/PO")
    private String customerReferenceNo;

    @ApiModelProperty(value = "nvocc证书编号")
    private String nvoccCertificateNo;

    @ApiModelProperty("税号")
    private String dutyParagraph;

    @ApiModelProperty("出口注册商代码")
    private String exportRegistrarCode;

    @ApiModelProperty("进出口编号(IEC)")
    private String importExportCode;

    @ApiModelProperty("PAN永久账户")
    private String permanentAccountNumber;

    @ApiModelProperty("ACID NUMBER")
    private String acidNumber;

    @ApiModelProperty("导出注册号")
    private String exporterRegistrationNumber;

    @ApiModelProperty("导出注册国家代码")
    private String exporterRegistrationCountryCode;

}
