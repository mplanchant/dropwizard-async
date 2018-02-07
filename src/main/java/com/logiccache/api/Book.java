package com.logiccache.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {
    private String id;
    private String title;
    private Author author;
}
