package com.hua.controller;

import java.util.List;

import com.hua.domain.Viewpoint;
import com.hua.service.ViewpointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/viewpoint")
public class ViewpointController {
    @Autowired
    private ViewpointService viewpointService;

    @PostMapping
    @ResponseBody
    public void up(Viewpoint viewpoint) {
        viewpointService.insert(viewpoint);
    }

    @GetMapping("/getall")
    public List<Viewpoint> getAll(){
        return viewpointService.getAllViewpoints();
    }

    @GetMapping("/getbytype/{type}")
    public List<Viewpoint> getByType(@PathVariable String type){
        return viewpointService.searchByType(type);
    }

    @GetMapping("/getbyid/{id}")
    public Viewpoint getById(@PathVariable Integer id){
        return viewpointService.search(id);
    }
}
