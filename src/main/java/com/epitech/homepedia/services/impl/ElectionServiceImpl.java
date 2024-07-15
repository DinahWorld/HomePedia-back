package com.epitech.homepedia.services.impl;

import com.epitech.homepedia.dto.ElectionDTO;
import com.epitech.homepedia.model.Election;
import com.epitech.homepedia.repository.ElectionRepository;
import com.epitech.homepedia.services.ElectionService;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ElectionServiceImpl implements ElectionService {
    private final ElectionRepository repository;
    private final ModelMapper mapper;
    private final SparkSession sparkSession;

    @Override
    public void addElection(ElectionDTO electionDTO) {
        repository.save(mapper.map(electionDTO, Election.class));
    }

    @Override
    public Map<String, Long> calculateElection(String city) {
        Dataset<Row> df = sparkSession.read().format("jdbc")
                .option("url", "jdbc:postgresql://0.0.0.0:5432/homepedia")
                .option("dbtable", "election")
                .option("user", "homepedia")
                .option("password", "homepedia")
                .load();

        Dataset<Row> filteredDf = df.filter(df.col("city").equalTo(city));
        Dataset<Row> result = filteredDf.groupBy("person_name")
                .sum("vote");

        List<Row> rows = result.collectAsList();
        Map<String, Long> electionResults = new HashMap<>();

        for (Row row : rows) {
            String personName = row.getString(0);
            Long totalVotes = row.getLong(1);

            electionResults.put(personName, totalVotes);
        }

        return electionResults;
    }
}