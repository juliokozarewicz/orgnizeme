package com.example.demo.utils.interfaces;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "categoryName"})
public interface CategoryProjection {

    String getId();
    String getCategoryName();
}
