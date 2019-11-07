package com.hua.domain;

import lombok.Data;

/**
 * File
 * url 服务器文件存储地址
 */
@Data
public class File{
    Long id;
    String filename;
    String url;
}