package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.model.Communes;
import com.epitech.homepedia.repository.CommunesRepository;
import com.epitech.homepedia.services.CommunesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommuneServiceImpl implements CommunesService {
    private final CommunesRepository communesRepository;

    @Override
    public void addCommune(BigDecimal price, String code) {
        var communes = new Communes();
        communes.setCode(code);
        communes.setPrice(price.intValue());
        communesRepository.save(communes);
    }

    @Override
    public List<Communes> getAll() {
        return communesRepository.findAll();
    }
}