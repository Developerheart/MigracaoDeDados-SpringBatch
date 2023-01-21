package com.developer.leonardo.migracaodedadosspringbatch.reader;

import com.developer.leonardo.migracaodedadosspringbatch.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.sql.Date;

@Configuration
public class MigrarDadosPessoasReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> pessoaFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("pessoaFlatFileItemReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("nome", "email", "dataNascimento", "idade", "id")
                .comments("-- ")
                .fieldSetMapper(filedSteMap())
                .build();

    }

    private FieldSetMapper<Pessoa> filedSteMap() {

        return new FieldSetMapper<Pessoa>() {
            @Override
            public Pessoa mapFieldSet(FieldSet fieldSet) throws BindException {
                return new Pessoa(
                        fieldSet.readInt("id"),
                        fieldSet.readString("nome"),
                        fieldSet.readString("email"),
                        new Date(fieldSet.readDate("dataNascimento", "yyyy-MM-dd HH:mm:ss").getTime()),
                        fieldSet.readInt("idade")
                );
            }
        };
    }
}
