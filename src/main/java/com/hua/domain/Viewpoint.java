package com.hua.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Viewpoint {
    private int id;
    private String province;
    private String city;
    private String area;
    private String name;
    private String type;
    private String nationality;
    private String grade;
    private String introduction;
    private String era;
    private String things;
    private String ticket;
    private String address;
    private String phone;
    private String time;
    private String images;
    private String latitude;
    private String longitude;

}
