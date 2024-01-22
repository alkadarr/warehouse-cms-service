package com.radev.project.dtos;

import lombok.Data;

@Data
public class MetaData {
    private Long totalCount;
    private Integer pageCount;
    private Integer currentPage;
    private Integer perPage;
}
