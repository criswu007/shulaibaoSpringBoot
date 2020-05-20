package com.example.mybatisplus.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: ADD Description(可选). <br/>
 *
 * @author use
 * Modification History:
 * Date             Author      Version     Description
 * ------------------------------------------------------------------
 * 2020-4-25 11:22  use      1.0        1.0 Version
 */
@Getter
@Setter
public class MultipartFileVO {

    private List<MultipartFile> file;

    private String name;
}
