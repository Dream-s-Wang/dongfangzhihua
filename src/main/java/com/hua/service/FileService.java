//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service;

import com.hua.domain.File;
import java.util.List;

public interface FileService {
    Long addFile(String filename, String url);

    List<File> getUnusedFile();

    void deleteFiles(List<File> files);

    void addFileRelationShip(String item, Long fid);

    File getFile(Long id);
}
