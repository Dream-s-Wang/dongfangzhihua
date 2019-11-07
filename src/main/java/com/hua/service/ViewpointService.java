package com.hua.service;

import java.util.List;

import com.hua.domain.Viewpoint;

public interface ViewpointService {
	void insert(Viewpoint viewpoint);
	void update(Viewpoint viewpoint);
	Viewpoint search(int id);
	List<Viewpoint> searchByType(String typeString);
	void deleteById(int id);
	List<Viewpoint> getAllViewpoints();
}
