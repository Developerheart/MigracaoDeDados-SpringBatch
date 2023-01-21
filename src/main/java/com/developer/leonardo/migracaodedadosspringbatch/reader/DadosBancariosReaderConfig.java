package com.developer.leonardo.migracaodedadosspringbatch.reader;

import com.developer.leonardo.migracaodedadosspringbatch.domain.DadoBancario;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

@Configuration
public class DadosBancariosReaderConfig {

    @Bean
    public FlatFileItemReader<DadoBancario> dadoBancarioFlatFileItemWriter() {
        return new FlatFileItemReaderBuilder<DadoBancario>()
                .name("dadoBancarioFlatFileItemWriter")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("pessoaId", "agencia", "conta", "banco", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMap())
//                .targetType(DadoBancario.class)
                .build();
    }

    private FieldSetMapper<DadoBancario> fieldSetMap() {
        return new FieldSetMapper<DadoBancario>() {
            @Override
            public DadoBancario mapFieldSet(FieldSet fieldSet) throws BindException {
                return new DadoBancario(
                        fieldSet.readInt("pessoaId"),
                        fieldSet.readInt("agencia"),
                        fieldSet.readInt("conta"),
                        fieldSet.readInt("banco"),
                        fieldSet.readInt("id")
                );
            }
        };
    }
}
