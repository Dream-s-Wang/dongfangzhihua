package com.hua.service.impl;

import java.util.List;

import com.hua.domain.Viewpoint;
import com.hua.service.ViewpointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ViewpointImpl implements ViewpointService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(Viewpoint viewpoint) {

		jdbcTemplate.update(
				"insert into `data`(id,province,city,area,name,type,nationality,introduction,address,route,time,phone,ticket,images) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				viewpoint.getIdInt(), viewpoint.getProvinceString(), viewpoint.getCityString(),
				viewpoint.getAreaString(), viewpoint.getNameString(), viewpoint.getTypeString(),
				viewpoint.getNationalityString(), viewpoint.getIntroductionString(), viewpoint.getAddressString(),
				viewpoint.getRouteString(), viewpoint.getTimeString(), viewpoint.getPhoneString(),
				viewpoint.getTicketString(), viewpoint.getImagesString());
	}

	@Override
	public void update(Viewpoint viewpoint) {

		jdbcTemplate.update(
				"update `data` set id=?, province=?, city=?, area=?, name=?,type=?, nationality=?, introduction=?, address=?, route=?, time=?, phone=?, ticket=?,images=? where id=?",
				viewpoint.getIdInt(), viewpoint.getProvinceString(), viewpoint.getCityString(),
				viewpoint.getAreaString(), viewpoint.getNameString(), viewpoint.getTypeString(),
				viewpoint.getNationalityString(), viewpoint.getIntroductionString(), viewpoint.getAddressString(),
				viewpoint.getRouteString(), viewpoint.getTimeString(), viewpoint.getPhoneString(),
				viewpoint.getTicketString(), viewpoint.getImagesString(), viewpoint.getIdInt());

	}

	@Override
	public Viewpoint search(int id) {
		List<Viewpoint> list = jdbcTemplate.query("select * from `data` where id=?", new Object[]{id}, new BeanPropertyRowMapper<Viewpoint>(Viewpoint.class));
        if(list!=null && list.size()>0){
            Viewpoint viewpoint = list.get(0);
            return viewpoint;
        }else{
            return null;
        }
	}

	@Override
	public List<Viewpoint> searchByType(String typeString) {
		List<Viewpoint> list = jdbcTemplate.query("select * from `data` where type=?", new Object[]{typeString}, new BeanPropertyRowMapper<Viewpoint>(Viewpoint.class));
		if(list.isEmpty()){
            return null;
        }
        return list;
		
	}

	@Override
	public void deleteById(int id) {
		jdbcTemplate.update("delete from `data` where id=?",id);
	}

	@Override
	public List<Viewpoint> getAllViewpoints() {

		List<Viewpoint> list = jdbcTemplate.query("select * from `data`", new Object[]{}, new BeanPropertyRowMapper<Viewpoint>(Viewpoint.class));
		if(list.isEmpty()){
            return null;
        }
		return list;
	}
}
