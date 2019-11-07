package com.hua.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.hua.domain.File;
import com.hua.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
/**
 * FileServiceImpl
 */
public class FileServiceImpl implements FileService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Long addFile(String filename, String url) {
        if(jdbcTemplate.update("insert into file(filename,url) values(?,?)",filename,url)>0){
            List<File> list = jdbcTemplate.query("select id from file where url=?", new Object[]{url}, new BeanPropertyRowMapper<File>(File.class));
            if(list!=null && list.size()>0){
                File file = list.get(0);
                return file.getId();
            }else{
                return null;
            }
        }
        else{
            return null;
        }
        
    }

    @Override
    public List<File> getUnusedFile() {
        return jdbcTemplate.query("SELECT * FROM file WHERE id NOT IN (SELECT fid FROM filerel)",
                new BeanPropertyRowMapper<>(File.class));
    }

    @Override
    public void deleteFiles(List<File> files) {
        List<Object[]> args = new LinkedList<>();
        for (File i : files) {
            args.add(new Object[]{i.getId()});
        }
        jdbcTemplate.batchUpdate("DELETE FROM filerel WHERE fid=?", args);
        jdbcTemplate.batchUpdate("DELETE FROM file WHERE id=?", args);
    }

    @Override
    public void addFileRelationShip(String item, Long fid) {
        jdbcTemplate.update("INSERT INTO filerel(fid,item) values(?,?)",fid,item);
    }

    @Override
    public File getFile(Long id) {
        List<File> list = jdbcTemplate.query("select * from file where id=?", new Object[]{id}, new BeanPropertyRowMapper<File>(File.class));
        if(list!=null && list.size()>0){
            File file = list.get(0);
            return file;
        }else{
            return null;
        }
        
    }
}