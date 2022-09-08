package com.example.mybatisplus.utils;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

import java.io.File;

@Slf4j
public class VideoEncodeUtil {

    /**
     * 视频转码，转成video标签可正常播放的协议格式
     * 视频：H264
     * 音频：AAC
     * @throws Exception
     */
    public static void encode() throws Exception{
        String testFilePath = log.getClass().getResource("/").getPath() + "static" + File.separator + "9d33afa18a323ae167fab5ee19ba5728.mp4";

        String outFile = log.getClass().getResource("/").getPath() + "static" + File.separator + "test.mp4";

        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(testFilePath);
        Frame captured_frame;
        FFmpegFrameRecorder recorder = null;
        try {
            grabber.start();
            recorder = new FFmpegFrameRecorder(outFile, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber.getFrameRate());
            recorder.setSampleRate(grabber.getSampleRate());
            recorder.setVideoBitrate(grabber.getVideoBitrate());
            recorder.setAspectRatio(grabber.getAspectRatio());
            recorder.setAudioBitrate(grabber.getAudioBitrate());
            recorder.setAudioOptions(grabber.getAudioOptions());

            recorder.start();

            while (true) {
                captured_frame = grabber.grabFrame();
                if (captured_frame == null) {
                    log.info("转码成功");
                    break;
                }
                recorder.record(captured_frame);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (recorder != null) {
                recorder.close();
            }
            if (grabber != null) {
                grabber.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            encode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
