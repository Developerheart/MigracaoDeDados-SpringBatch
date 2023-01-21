package com.developer.leonardo.migracaodedadosspringbatch.writer;

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
public class MigrarDadosPessoasWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Pessoa> pessoaJdbcBatchItemWriter(@Qualifier("appDataSouce") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(dataSource)
                .sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPSitemWriter())
                .build();
    }

    private ItemPreparedStatementSetter<Pessoa> itemPSitemWriter() {
        return new ItemPreparedStatementSetter<Pessoa>() {
            @Override
            public void setValues(Pessoa pessoa, PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, pessoa.id());
                preparedStatement.setString(2, pessoa.nome());
                preparedStatement.setString(3, pessoa.email());
                preparedStatement.setDate(4, pessoa.dataNascimento());
                preparedStatement.setInt(5, pessoa.idade());
            }
        };
    }

}
