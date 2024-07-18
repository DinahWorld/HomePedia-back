package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departements")
@Data
public class Departements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Integer price;
}