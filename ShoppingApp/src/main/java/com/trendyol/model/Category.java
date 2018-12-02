package com.trendyol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    private Category subCategory;
    private String title;

    public Category(String title) {
        this.title = title;

    }
}
