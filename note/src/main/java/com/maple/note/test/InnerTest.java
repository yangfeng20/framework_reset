package com.maple.note.test;

import com.google.common.collect.Lists;

/**
 * @author yangfeng
 * @date : 2023/6/9 16:21
 * desc:
 */

public class InnerTest {
    public static void main(String[] args) throws Exception{
        String toStringStr = "JobStatInfoVO(companyId=60089, callJobId=5688807, finishedStatus=2, duration=0, hangUpType=0, chatRound=0, properties={\"anchorTypeWords\":\"customerIntention\",\"isVirtualPhone\":\"false\",\"policyName\":\"贷中固额提升_哈哈_230404-B1组(by)\",\"callJobId\":\"5688807\",\"客户称呼\":\"先生\",\"nodeLogId\":\"20154069305\",\"callStartTime\":\"2024-01-12 17:50:17\",\"linePeriodNum\":\"0\",\"释放额度\":\"5200\",\"marketPlanName\":\"5688807-贷中固额提升_哈哈_230404-B1组(by)\",\"联系方式\":\"1LpiIvxjSOPDdNDNoGD3wSeM9POWacNdlkovXSJPb/E=\",\"aiSeatBillPeriodNum\":\"0\",\"userId\":\"533096561564413952\",\"taskStartTime\":\"2024-01-12 17:50:15\",\"姓名\":\"谢红民\",\"companyId\":\"60089\",\"maFlag\":\"callJob\",\"executeLogId\":\"2906438977\",\"wechatPushStatus\":\"0\",\"客户名称\":\"未知客户\",\"workflowId\":\"39781\"}, onIntent=null, ifUpdateCountTotal=true, lastFinishedStatus=null, calledTimes=1, linePeriodCount=0, aiSeatBillPeriodCount=0, overTwoChatRoundCount=0)";
        String result = ToStringUtils.toJSONString(toStringStr, Lists.newArrayList("properties", "dialogueRoundsJson"));
        System.out.println(result);
    }
}