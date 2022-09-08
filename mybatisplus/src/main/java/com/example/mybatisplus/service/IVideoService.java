package com.example.mybatisplus.service;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IVideoService {
    void playVideo(RequestFacade request, ResponseFacade response);
}
