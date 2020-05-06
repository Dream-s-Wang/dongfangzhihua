//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.controller;

import com.hua.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({ "/file" })
public class FileController {
    @Autowired
    private FileService fileService;
    @Value("${hua.uppath}")
    private String upPath;

    public FileController() {
    }

    @PostMapping
    @ResponseBody
    public Long up(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String fname = this.upPath + Long.toHexString(System.currentTimeMillis());
        File localFile = new File(fname);
        file.transferTo(localFile);
        Long res = this.fileService.addFile(file.getOriginalFilename(), fname);
        System.out.println(res);
        return res;
    }

    @PostMapping({ "/rel/" })
    @ResponseBody
    public void uprel(Long fid, String item) {
        this.fileService.addFileRelationShip(item, fid);
    }

    @GetMapping({ "/{id}" })
    public void download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, IOException {
        com.hua.domain.File file = this.fileService.getFile(id);
        InputStream inputStream = new FileInputStream(new File(file.getUrl()));
        Throwable var6 = null;

        try {
            OutputStream outputStream = response.getOutputStream();
            Throwable var8 = null;

            try {
                response.setContentType("application/x-download");
                response.setCharacterEncoding("utf-8");
                String fileName = file.getFilename();
                response.setHeader("Content-Disposition",
                        "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"));
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
                System.out.println(file.toString());
            } catch (Throwable var31) {
                var8 = var31;
                throw var31;
            } finally {
                if (outputStream != null) {
                    if (var8 != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable var30) {
                            var8.addSuppressed(var30);
                        }
                    } else {
                        outputStream.close();
                    }
                }

            }
        } catch (Throwable var33) {
            var6 = var33;
            throw var33;
        } finally {
            if (inputStream != null) {
                if (var6 != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable var29) {
                        var6.addSuppressed(var29);
                    }
                } else {
                    inputStream.close();
                }
            }

        }

    }

    @Scheduled(cron = "${hua.filecron}")
    public void deleteUnusedFiles() {
        List<com.hua.domain.File> unusedFiles = this.fileService.getUnusedFile();
        List<com.hua.domain.File> deletedFiles = new LinkedList();
        Iterator var3 = unusedFiles.iterator();

        while (var3.hasNext()) {
            com.hua.domain.File i = (com.hua.domain.File) var3.next();
            File file = new File(this.upPath + i.getFilename());
            if (file.delete()) {
                deletedFiles.add(i);
            } else {
                System.out.println("删除" + i.getFilename() + "失败。");
            }
        }

        this.fileService.deleteFiles(deletedFiles);
    }
}
