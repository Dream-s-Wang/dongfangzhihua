//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hua.service;

import com.hua.domain.Viewpoint;
import java.util.List;

public interface ViewpointService {
    void insert(Viewpoint viewpoint);

    void update(Viewpoint viewpoint);

    Viewpoint search(int id);

    List<Viewpoint> searchByType(String typeString);

    void deleteById(int id);

    List<Viewpoint> getAllViewpoints();
}
