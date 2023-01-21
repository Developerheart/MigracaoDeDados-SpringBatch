package com.developer.leonardo.migracaodedadosspringbatch.step;

import com.developer.leonardo.migracaodedadosspringbatch.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigrarDadosPessoasStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step migrarDadosPessoasStep(
            @Qualifier("pessoaFlatFileItemReader") FlatFileItemReader<Pessoa> pessoaItemReader,
            ClassifierCompositeItemWriter<Pessoa> pessoaClassifierCompositeItemWriter,
            FlatFileItemWriter<Pessoa> pessoaInvaliaItemWriter) {
        return stepBuilderFactory
                .get("migrarDadosPessoasStep")
                .<Pessoa, Pessoa>chunk(2000)
                .reader(pessoaItemReader)
                .writer(pessoaClassifierCompositeItemWriter)
                .stream(pessoaInvaliaItemWriter)
                .build();
    }


}
