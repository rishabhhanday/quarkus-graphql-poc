package com.poc.model;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@ToString
public class Hero {
    public String name;
    public String surname;
    public Double height;
    public Integer mass;
    public Boolean darkSide;
    public LightSaber lightSaber;
    public List<Integer> episodeIds = new ArrayList<>();
}
