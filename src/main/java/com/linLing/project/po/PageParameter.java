package com.linLing.project.po;

import lombok.Data;

import java.util.HashMap;

@Data
public class PageParameter {
    int page;
    int size;
    String dir;
    String sort;
    HashMap parameters;
}

