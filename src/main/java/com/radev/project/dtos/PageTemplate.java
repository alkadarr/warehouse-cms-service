package com.radev.project.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PageTemplate {
    private List<?> _items;
    private MetaData _meta;
}

