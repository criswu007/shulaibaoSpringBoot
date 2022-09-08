package com.example.mybatisplus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String html = "<p style=\"text-align:center;\">\n" +
                "\t<br />\n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n" +
                "\t<b><span style=\"font-family:SimSun;font-size:14px;\">江苏省南京市六合区人民法院</span></b><b></b> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n" +
                "\t<b><span style=\"font-family:SimSun;font-size:14px;\">法庭笔录</span></b><b></b> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">时间：</span><span style=\"font-family:SimSun;font-size:14px;\">2020年4月27日16时50分至17时30分</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">地点：南京市六合区人民法院第五法庭</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">案号：（</span><span style=\"font-family:SimSun;font-size:14px;\">2019）苏0116民初5784号</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">案</span><span style=\"font-family:SimSun;font-size:14px;\"> 由：民间借贷纠纷</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审</span><span style=\"font-family:SimSun;font-size:14px;\"> 判 &nbsp;长：张 &nbsp;琼</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">人民陪审员：张菊花</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">人民陪审员：</span><span style=\"font-family:SimSun;font-size:14px;\">王</span><span style=\"font-family:SimSun;font-size:14px;\"> 尧</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">书</span><span style=\"font-family:SimSun;font-size:14px;\"> 记 &nbsp;员：宋嘉雯</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">（开庭审理前，书记员应当查明当事人和其他诉讼参与人是否到庭，落座后宣布法庭纪律，请审判人员入庭就座）</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审判员：根据最高人民法院司法公开的相关要求，将对本次庭审进行网络直播。（敲击法槌）现在开庭。首先核对当事人和其他诉讼参加人的基本信息。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原告：林海，男，</span><span style=\"font-family:SimSun;font-size:14px;\">1980年6月21日生，居民身份证号码320123198006210019, 汉族，住南京市六合区雄州街道高余村农商街210-25号。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被告：马恩骏，男，</span><span style=\"font-family:SimSun;font-size:14px;\">1991年9月5日生，居民身份证号码320123199109054615，汉族，住南京市六合区冶山街道东王社区青龙马庄11号。（未到庭）</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">委托诉讼代理人：王银生，男，</span><span style=\"font-family:SimSun;font-size:14px;\">1973年8月28日生，居民身份证号码320113197308282010，汉族，住南京市六合区冶山街道四马北路40-25号。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.2500pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：原告对出庭人员有无异议？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原：没有。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：被告对出庭人员有无异议？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被：没有。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：经核对，各方当事人和其他诉讼参加人均符合法律规定，可以参加本案诉讼活动。南京市六合区人民法院依照《中华人民共和国民事诉讼法》第一百三十四条规定，今天依法适用普通程序，公开开庭审理（</span><span style=\"font-family:SimSun;font-size:14px;\">2019）苏0116民初5784号原告林海诉被告马恩骏民间借贷纠纷一案。本案由审判员张琼、人民陪审员张菊花、人民陪审员王尧组成合议庭，由审判员张琼担任审判长，刘云担任法官助理，由书记员宋嘉雯担任记录。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">告知当事人有关诉讼权利和义务。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：当事人可以提出回避申请。原告是否申请回避？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原：不申请。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：被告是否申请回避？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被：不申请。</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：双方还有无其他证据和事实需要补充？</span><span style=\"font-family:SimSun;font-size:14px;\"></span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原告：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被代：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：法庭调查结束，双方发表辩论意见？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原告：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被代：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：法定辩论终结，下面由原告陈述最后意见？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原告：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：下面由被告陈述最后意见？</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被代：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审：现在闭庭。（敲击法槌）</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">原告签名：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\"> </span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">被告签名：<img id=\"111111\" src=\"http://192.168.2.103:8087/dzzz/temp/132720190401000071/448faf86a6f1468993fa20844da08d6e/1a8ce3b7b11145c0a5bc65dd9f209328.jpg\" width=\"100px\" height=\"40px\" style=\"object-fit: cover\">" +
                "<img id=\"222222\" src=\"http://192.168.2.103:8087/dzzz/temp/132720190401000071/448faf86a6f1468993fa20844da08d6e/1a8ce3b7b11145c0a5bc65dd9f209328.jpg\" width=\"100px\" height=\"40px\" style=\"object-fit: cover\"></span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\"> </span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">审判长签名：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\"> </span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">人民陪审员签名：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\"> </span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\" style=\"text-indent:27.8000pt;\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\">书记员签名：</span> \n" +
                "</p>\n" +
                "<p class=\"MsoNormal\">\n" +
                "\t<span style=\"font-family:SimSun;font-size:14px;\"> </span> \n" +
                "</p>\n" +
                "<span style=\"font-family:SimSun;font-size:14px;\"></span> \n" +
                "<p>\n" +
                "\t<br />\n" +
                "</p>";
        String type = "被告签名：";
        String preHtml = html.substring(0, html.lastIndexOf(type));
        String subHtml = html.substring(html.lastIndexOf(type));
//        System.out.println(subHtml);
        Pattern p_img = Pattern.compile(type + "<(img|IMG)(.*?)(/>|></img>|>)");
        Matcher m_img = p_img.matcher(subHtml);
        boolean result_img = m_img.find();

        if (result_img) {
            while (result_img) {
                String str_img = m_img.group(0);
                subHtml = subHtml.replaceFirst(str_img, "");
                System.out.println(preHtml + type + subHtml);
                result_img = m_img.find();
            }
        }

    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
