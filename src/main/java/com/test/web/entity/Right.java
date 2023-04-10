package com.test.web.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Right {
    private Long id;

    @NotBlank
    private String name;

    private String description;
}