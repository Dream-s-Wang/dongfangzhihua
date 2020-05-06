//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.controller;

import com.hua.domain.Viewpoint;
import com.hua.service.ViewpointService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/viewpoint" })
public class ViewpointController {
    @Autowired
    private ViewpointService viewpointService;

    public ViewpointController() {
    }

    @PostMapping
    @ResponseBody
    public void up(Viewpoint viewpoint) {
        this.viewpointService.insert(viewpoint);
    }

    @GetMapping({ "/getall" })
    @ResponseBody
    public List<Viewpoint> getAll() {
        return this.viewpointService.getAllViewpoints();
    }

    @GetMapping({ "/getbytype/{type}" })
    @ResponseBody
    public List<Viewpoint> getByType(@PathVariable String type) {
        return this.viewpointService.searchByType(type);
    }

    @GetMapping({ "/getbyid/{id}" })
    @ResponseBody
    public Viewpoint getById(@PathVariable Integer id) {
        return this.viewpointService.search(id);
    }

    @PostMapping({ "/delete/{id}" })
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        this.viewpointService.deleteById(id);
    }

    @PostMapping({ "/update" })
    @ResponseBody
    public void updateOne(Viewpoint viewpoint) {
        this.viewpointService.update(viewpoint);
    }
}
