//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service.impl;

import com.hua.domain.Viewpoint;
import com.hua.service.ViewpointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewpointImpl implements ViewpointService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ViewpointImpl() {
    }

    @Override
    public void insert(Viewpoint viewpoint) {
        this.jdbcTemplate.update(
                "insert into `data`(id,province,city,area,name,type,nationality,introduction,address,things,time,phone,ticket,images,grade,era,latitude,longitude) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                new Object[] { viewpoint.getId(), viewpoint.getProvince(), viewpoint.getCity(), viewpoint.getArea(),
                        viewpoint.getName(), viewpoint.getType(), viewpoint.getNationality(),
                        viewpoint.getIntroduction(), viewpoint.getAddress(), viewpoint.getThings(), viewpoint.getTime(),
                        viewpoint.getPhone(), viewpoint.getTicket(), viewpoint.getImages(), viewpoint.getGrade(),
                        viewpoint.getEra(), viewpoint.getLatitude(), viewpoint.getLongitude() });
    }

    @Override
    public void update(Viewpoint viewpoint) {
        this.jdbcTemplate.update(
                "update `data` set  province=?, city=?, area=?, name=?,type=?, nationality=?, introduction=?, address=?, things=?, time=?, phone=?, ticket=?,images=? ,grade=? ,era=?,latitude=?,longitude=? where id=?",
                new Object[] { viewpoint.getProvince(), viewpoint.getCity(), viewpoint.getArea(), viewpoint.getName(),
                        viewpoint.getType(), viewpoint.getNationality(), viewpoint.getIntroduction(),
                        viewpoint.getAddress(), viewpoint.getThings(), viewpoint.getTime(), viewpoint.getPhone(),
                        viewpoint.getTicket(), viewpoint.getImages(), viewpoint.getGrade(), viewpoint.getEra(),
                        viewpoint.getLatitude(), viewpoint.getLongitude(), viewpoint.getId() });
    }

    @Override
    public Viewpoint search(int id) {
        List<Viewpoint> list = this.jdbcTemplate.query("select * from `data` where id=?", new Object[] { id },
                new BeanPropertyRowMapper(Viewpoint.class));
        if (list != null && list.size() > 0) {
            Viewpoint viewpoint = (Viewpoint) list.get(0);
            return viewpoint;
        } else {
            return null;
        }
    }

    @Override
    public List<Viewpoint> searchByType(String typeString) {
        List<Viewpoint> list = this.jdbcTemplate.query("select * from `data` where type=?", new Object[] { typeString },
                new BeanPropertyRowMapper(Viewpoint.class));
        return list.isEmpty() ? null : list;
    }

    @Override
    public void deleteById(int id) {
        this.jdbcTemplate.update("delete from `data` where id=?", new Object[] { id });
    }

    @Override
    public List<Viewpoint> getAllViewpoints() {
        List<Viewpoint> list = this.jdbcTemplate.query("select * from `data`", new Object[0],
                new BeanPropertyRowMapper(Viewpoint.class));
        return list.isEmpty() ? null : list;
    }
}
