package com.example.mybatisplus.controller;

import com.example.mybatisplus.service.IVideoService;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/v1/video")
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @RequestMapping("/play")
    public void playVideo(HttpServletRequest request, HttpServletResponse response) {
        RequestFacade requestFacade = (RequestFacade) request;
        ResponseFacade responseFacade = (ResponseFacade) response;
        videoService.playVideo(requestFacade, responseFacade);
    }
}
