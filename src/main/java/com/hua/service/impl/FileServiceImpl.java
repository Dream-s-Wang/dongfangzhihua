//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service.impl;

import com.hua.domain.File;
import com.hua.service.FileService;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FileServiceImpl() {
    }

    @Override
    public Long addFile(String filename, String url) {
        if (this.jdbcTemplate.update("insert into file(filename,url) values(?,?)",
                new Object[] { filename, url }) > 0) {
            List<File> list = this.jdbcTemplate.query("select id from file where url=?", new Object[] { url },
                    new BeanPropertyRowMapper(File.class));
            if (list != null && list.size() > 0) {
                File file = (File) list.get(0);
                return file.getId();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<File> getUnusedFile() {
        return this.jdbcTemplate.query("SELECT * FROM file WHERE id NOT IN (SELECT fid FROM filerel)",
                new BeanPropertyRowMapper(File.class));
    }

    @Override
    public void deleteFiles(List<File> files) {
        List<Object[]> args = new LinkedList();
        Iterator var3 = files.iterator();

        while (var3.hasNext()) {
            File i = (File) var3.next();
            args.add(new Object[] { i.getId() });
        }

        this.jdbcTemplate.batchUpdate("DELETE FROM filerel WHERE fid=?", args);
        this.jdbcTemplate.batchUpdate("DELETE FROM file WHERE id=?", args);
    }

    @Override
    public void addFileRelationShip(String item, Long fid) {
        this.jdbcTemplate.update("INSERT INTO filerel(fid,item) values(?,?)", new Object[] { fid, item });
    }

    @Override
    public File getFile(Long id) {
        List<File> list = this.jdbcTemplate.query("select * from file where id=?", new Object[] { id },
                new BeanPropertyRowMapper(File.class));
        if (list != null && list.size() > 0) {
            File file = (File) list.get(0);
            return file;
        } else {
            return null;
        }
    }
}
