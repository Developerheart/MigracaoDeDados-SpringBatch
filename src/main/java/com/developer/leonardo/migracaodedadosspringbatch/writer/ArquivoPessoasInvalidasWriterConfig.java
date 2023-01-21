package com.developer.leonardo.migracaodedadosspringbatch.writer;

import com.developer.leonardo.migracaodedadosspringbatch.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoPessoasInvalidasWriterConfig {

    @Bean
    public FlatFileItemWriter<Pessoa> pessoaFlatFileItemWriter() {
        return new FlatFileItemWriterBuilder<Pessoa>()
                .name("pessoaFlatFileItemWriter")
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .delimited()
                .names("id")
                .build();
    }


}
