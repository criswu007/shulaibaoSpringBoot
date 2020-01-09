package com.example.mybatisplus.controller;

import com.example.mybatisplus.http.HttpClientResult;
import com.example.mybatisplus.http.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-1-9 10:49  use      1.0        1.0 Version
 */
@Controller
@RequestMapping("/http")
public class HttpController {
    @PostMapping("test.do")
    public void testHttp() throws Exception {
        HttpClientResult httpClientResult = HttpClientUtils.doGet("http://www.baidu.com");
        String html = httpClientResult.getContent();
        Document doc = Jsoup.parse(html);
        Elements imgs = doc.getElementsByTag("img");
        System.out.println("---" + this.getClass().getResource("/").getPath());
        for (Element img : imgs) {
            String imgSrc = img.attr("src");
            if (imgSrc.startsWith("//")) {
                imgSrc = "http:" + imgSrc;
            }

            String targetPath = "D:" + File.separator + UUID.randomUUID() + ".png";
            System.out.println(targetPath);
            Files.copy(new URL(imgSrc).openStream(), Paths.get(targetPath));
        }
    }
}
