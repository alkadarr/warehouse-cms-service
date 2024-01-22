package com.radev.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageTemplate {
    private List<?> _items;
    private MetaData _meta;
}

