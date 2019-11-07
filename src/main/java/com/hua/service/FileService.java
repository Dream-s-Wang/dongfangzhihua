package com.hua.service;

import java.util.List;

import com.hua.domain.File;

/**
 * FileService
 */
public interface FileService {

    /**
     * 添加文件
     * @param filename 文件名
     * @param url 文件地址
     * @return 文件id
     */
    Long addFile(String filename,String url);
    
    /**
     * 获取所有未使用的图片
     * @return
     */
    List<File> getUnusedFile();

    /**
     * 一次性删除多个文件
     * @param files
     * @return
     */
    void deleteFiles(List<File> files);

    /**
     * 更新文件依赖关系
     * @param item
     * @param fid
     */
    void addFileRelationShip(String item,Long fid);

    /**
     * 获取文件
     * @param id
     * @return file
     */
    File getFile(Long id);
}