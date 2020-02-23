package com.linLing.project.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Pages{
    private Integer page;
    private Integer size;
    private Long counts;
    private List rows;
}
