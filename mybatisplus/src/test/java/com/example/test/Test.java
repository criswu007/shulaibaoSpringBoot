package com.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2019-12-3 9:54  use      1.0        1.0 Version
 */
public class Test {
    public static void main (String[] args) {
        JSONObject jo = new JSONObject();
        String arrString = "{\"test\" : \"      1.被告陈龙归还原告杭州联合农村商业银行股份有限公司借款本金72871.01元、利息\n2739.87元、手续费15312.57元、违约金2390.44元(暂算至2019年8月15日，此后至实际清偿之日止的利息及违约金以72871.01元为基数，按照年利率\n0.18的标准另行计算）；      2.本案案件受理费、保全费由各被告负担。\"}";
        jo = JSONObject.parseObject(arrString);
        System.out.println(jo.toString());
    }
}
