package com.example.mybatisplus.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.mybatisplus.pojo.vo.MultipartFileVO;
import com.example.mybatisplus.service.IEajJzService;
import com.example.mybatisplus.utils.ConcurrentUtils;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wudb
 * @since 2019-11-26
 */
@Api("测试接口")
@RestController
@RequestMapping("/jzgl")
@Slf4j
public class EajJzController {

    private volatile Integer count = 0;

    private AtomicInteger atomicCount = new AtomicInteger(0);

    @Autowired
    @Qualifier("JzglService")
    private IEajJzService eajJzService;

    @Value("${tempFile.path.dir}")
    private String fileDir;

    @ApiOperation(value = "测试多线程接口")
    @PostMapping("test.do")
    public void Test(@RequestBody JSONObject jo) throws Exception {
//        synchronized (this) {
//            Thread.sleep(10000);
//        }
//        System.out.println("test.do");
//        EajJzEntity eajJzEntity = new EajJzEntity();
//        eajJzEntity.setAhdm("123");
//        eajJzService.test(jo, eajJzEntity);

//        ArrayList<Callable> tasks = new ArrayList<Callable>();


        Integer taskSize = 10000;

        CountDownLatch countDownLatch = new CountDownLatch(taskSize);

        StopWatch stopWatch = new StopWatch("test");
        stopWatch.start();

        ConcurrentUtils.getInstance();

        for (int i=0; i<taskSize; i++) {
            ConcurrentUtils.submitRunTask(() -> {
//                atomicCount.addAndGet(1);
//                synchronized (this) {
                    count++;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                calculateCount();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();

        System.out.println("count:" + count);
        System.out.println("atomicCount:" + atomicCount);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public synchronized void calculateCount() {
        count ++;
    }

    @PostMapping("test1.do")
    public void Test1() {
//        throw new RuntimeException();
        eajJzService.test1();
    }

    @PostMapping("test2.do")
    public void Test2() {
        eajJzService.test2();
    }

    @PostMapping("test3.do")
    public void Test3() {
        eajJzService.test3();
    }

    /**
     * 文件上传demo
     * @param file
     * @param name
     * @throws Exception
     */
    @RequestMapping("/test4.do")
//    public void Test4(List<MultipartFile> fileList, @RequestParam(value = "name") String name, HttpServletRequest request) {
    public void Test4(@RequestBody MultipartFileVO multipartFileVO, HttpServletRequest request) {
        String contentType = request.getHeader("Content-Type");

//        fileList.stream().forEach((file) -> {
//            try {
//                InputStream inputStream = file.getInputStream();
//                String dir = ResourceUtils.getURL("classpath:").getPath() + "temp/" + UUID.randomUUID() + ".png";
//                File tempfile = new File(dir);
//                if (!tempfile.getParentFile().exists()) {
//                    tempfile.getParentFile().mkdirs();
//                }
//                if (!tempfile.exists()) {
//                    tempfile.createNewFile();
//                }
//                OutputStream outputStream = new FileOutputStream(tempfile);
//                IOUtils.copy(inputStream, outputStream);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
    }

    /**
     * 下载影像卷文件
     * @param
     * @return
     */
    @RequestMapping("/downYxjFile.do")
//    @ResponseBody
    public ResponseEntity<String> downYxjFile() throws Exception {
        String path = this.getClass().getResource("/").getPath() + "../temp/" + UUID.randomUUID() + ".txt";
        File tempfile = new File(path);
        if (!tempfile.getParentFile().exists()) {
            tempfile.getParentFile().mkdirs();
        }
        if (!tempfile.exists()) {
            tempfile.createNewFile();
        }
        ClassPathResource classPathResource = new ClassPathResource("/temp/recently.txt");
        String path1 = classPathResource.getPath();
        InputStream inputStream = classPathResource.getInputStream();
        OutputStream outputStream = new FileOutputStream(tempfile);
        FileCopyUtils.copy(inputStream, outputStream);
        return ResponseEntity.ok().build();
//        String yxjFile = "C:/Users/use/Desktop/常见编码及乱码的处理.pdf";
//        File file = new File(yxjFile);
//        String filename = file.getName();
//        System.out.println(filename);
//        try {
//            filename = URLEncoder.encode(filename, "UTF-8");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(filename);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Disposition", "attachment;filename=" + filename);
//        httpHeaders.add("Content-Length", "" + file.length());


//        MediaType mediaType = new MediaType(MediaType.APPLICATION_OCTET_STREAM, Charset.forName("utf-8"));
//        MediaType mediaType = new MediaType(MediaType.APPLICATION_FORM_URLENCODED, Charset.forName("utf-8"));

//        httpHeaders.setContentType(mediaType);
//        return ResponseEntity.ok().contentType(mediaType).headers(httpHeaders).body(new FileSystemResource(file));
//        return ResponseEntity.ok().body("失败，必填校验失败");
//        return new ResponseEntity(filename, httpHeaders, HttpStatus.OK);
//        return new ResponseEntity<String>(filename, HttpStatus.OK);
//        return ResponseEntity.ok().build();

    }

    @GetMapping("/test5.do")
    public void test5() throws Exception {
        String path = this.getClass().getResource("/").getPath() + "../temp/" + UUID.randomUUID() + ".txt";
//        String path = fileDir + "/" + UUID.randomUUID() + ".txt";
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
    }
}
