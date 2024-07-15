package com.epitech.homepedia.dto;

import lombok.Data;

@Data
public class ElectionDTO {
    private String city;
    private Integer postalCode;
    private String personName;
    private String personFullName;
    private String vote;
}