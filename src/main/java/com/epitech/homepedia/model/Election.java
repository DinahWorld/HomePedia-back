package com.epitech.homepedia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "election")
@Table(name = "election")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Election {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @JoinColumn(name = "com_name")
    private String city;

    @JoinColumn(name = "com_code")
    private Integer postalCode;

    @JoinColumn(name = "nom")
    private String personName;

    @JoinColumn(name = "prenom")
    private String personFullName;

    @JoinColumn(name = "voix")
    private Integer vote;
}