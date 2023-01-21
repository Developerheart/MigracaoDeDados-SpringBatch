package com.developer.leonardo.migracaodedadosspringbatch.step;

import com.developer.leonardo.migracaodedadosspringbatch.domain.DadoBancario;
import com.developer.leonardo.migracaodedadosspringbatch.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarDadosBancariosStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step migrarDadosBancariosStep(
            @Qualifier("dadoBancarioFlatFileItemWriter") ItemReader<DadoBancario> dadoBancarioItemReader,
            @Qualifier("dadoBancarioJdbcBatchItemWriter") ItemWriter<DadoBancario> dadoBancarioItemWriter) {
        return stepBuilderFactory
                .get("migrarDadosBancariosStep")
                .<DadoBancario, DadoBancario>chunk(200)
                .reader(dadoBancarioItemReader)
                .writer(dadoBancarioItemWriter)
                .build();
    }


}
