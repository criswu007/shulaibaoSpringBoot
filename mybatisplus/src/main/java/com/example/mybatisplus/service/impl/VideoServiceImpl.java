package com.example.mybatisplus.service.impl;

import cn.hutool.core.io.IoUtil;
import com.example.mybatisplus.service.IVideoService;
import com.example.mybatisplus.utils.VideoEncodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;

@Service
@Slf4j
public class VideoServiceImpl implements IVideoService {

    @Override
    public void playVideo(RequestFacade request, ResponseFacade response) {
        String testFilePath = this.getClass().getResource("/").getPath() + "static" + File.separator + "9d33afa18a323ae167fab5ee19ba5728.mp4";

        String fileName = "ceshi.mp4";
        RandomAccessFile targetFile = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            response.reset();
            String rangeString = request.getHeader("Range");

            File file = new File(testFilePath);

            targetFile = new RandomAccessFile(file, "r");

            long fileLength = targetFile.length();
            long requestSize = fileLength;
            if (StringUtils.hasText(rangeString)) {
                long requestStart = 0, requestEnd = 0;
                String [] ranges = rangeString.split("=");
                if (ranges.length > 1) {
                    String[] rangeDatas = ranges[1].split("-");
                    requestStart = Integer.parseInt(rangeDatas[0]);
                    if (rangeDatas.length > 1) {
                        requestEnd = Integer.parseInt(rangeDatas[1]);
                    }
                }
                if (requestEnd != 0 && requestEnd > requestStart) {
                    requestSize = requestEnd - requestStart + 1;
                }
                response.setHeader(HttpHeaders.ACCEPT, "bytes");
                response.setHeader(HttpHeaders.CONTENT_TYPE, "video/mp4");
                if (!StringUtils.hasText(rangeString)) {
                    response.setHeader(HttpHeaders.CONTENT_LENGTH, fileLength + "");
                } else {
                    long length;
                    log.info("fileLength:{}", fileLength);
                    if (requestEnd > 0) {
                        length = requestEnd - requestStart + 1;
                        response.setHeader(HttpHeaders.CONTENT_LENGTH, length + "");
                        response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + requestStart + "-" + requestEnd + "/" + fileLength);
                    } else {
                        length = fileLength - requestStart;
                        response.setHeader(HttpHeaders.CONTENT_LENGTH, length + "");
                        response.setHeader(HttpHeaders.CONTENT_RANGE, "bytes " + requestStart + "-" + (fileLength - 1) + "/" + fileLength);
                    }
                }
                // 分段下载视频返回206
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                // 设置targetFile，从自定义位置开始读取数据
                targetFile.seek(requestStart);
            } else {
                response.setHeader(HttpHeaders.CONTENT_RANGE, fileLength + "");
            }

            byte [] cache = new byte[4096];
            int byteRead;
            int allReadBytes = 0;
            while ((byteRead = targetFile.read(cache)) != -1) {
                allReadBytes += byteRead;
                log.info("allReadBytes:{}", allReadBytes);
                outputStream.write(cache, 0, byteRead);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            IoUtil.close(outputStream);
            IoUtil.close(targetFile);
        }
    }
}
