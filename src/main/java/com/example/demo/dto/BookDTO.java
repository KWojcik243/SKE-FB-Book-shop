package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private int id;
    private String title;
    private String pngPath;
    private int ageGroup;
    private float rating;
    private long isbn;
    private int amount;
    private int categoryId;
    private List<Integer> authorIds;
}
