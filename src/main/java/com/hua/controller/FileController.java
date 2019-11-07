package com.hua.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hua.domain.File;
import com.hua.service.FileService;

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
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Value("${hua.uppath}")
    private String upPath;

    @PostMapping
    @ResponseBody
    public Long up(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        String fname = upPath + Long.toHexString(System.currentTimeMillis());
        java.io.File localFile = new java.io.File(fname);
        file.transferTo(localFile);
        Long res = fileService.addFile(file.getOriginalFilename(), fname);
        System.out.println(res);
        return res;
    }

    @PostMapping("/rel/")
    @ResponseBody
    public void uprel(Long fid,String item){
        fileService.addFileRelationShip(item, fid);
    }
    @GetMapping("/{id}")
    public void download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
            throws FileNotFoundException, IOException {
        File file = fileService.getFile(id);
        try(
            InputStream inputStream = new FileInputStream(new java.io.File(file.getUrl()));
            OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.setCharacterEncoding("utf-8");
            String fileName = file.getFilename();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            System.out.println(file.toString());
        }
    }
    /**
     * 定时删除未被使用的文件
     * 开发过程中可以把@Scheduled注解注释掉
     */
    @Scheduled(cron="${hua.filecron}")
    public void deleteUnusedFiles() {
        // log.debug("正在扫描未使用的文件……");
        List<File> unusedFiles = fileService.getUnusedFile();
        List<File> deletedFiles = new LinkedList<>();
        for (File i : unusedFiles) {
            // log.trace("未使用的文件：" + i);
            java.io.File file = new java.io.File(upPath + i.getFilename());
            if (file.delete()) {
                deletedFiles.add(i);
                // log.debug("删除{}成功。",i.getFilename());
            } else {
                System.out.println("删除" + i.getFilename() + "失败。");
                // log.warn("删除{}失败", i.getFilename());
            }
        }
        // log.debug("删除数据库中的记录共{}条……",deletedFiles.size());
        fileService.deleteFiles(deletedFiles);
    }
}