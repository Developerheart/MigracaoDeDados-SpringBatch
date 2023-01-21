package com.developer.leonardo.migracaodedadosspringbatch.writer;

import com.developer.leonardo.migracaodedadosspringbatch.domain.DadoBancario;
import com.developer.leonardo.migracaodedadosspringbatch.domain.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class DadosBancariosWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DadoBancario> dadoBancarioJdbcBatchItemWriter(@Qualifier("appDataSouce") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<DadoBancario>()
                .dataSource(dataSource)
                .sql("INSERT INTO dados_bancarios (id, pessoa_id, agencia, conta, banco) VALUES (:id, :pessoaId, :agencia, :conta, :banco)")
                .beanMapped()
                .build();
    }

}
