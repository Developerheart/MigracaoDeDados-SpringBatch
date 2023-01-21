package com.developer.leonardo.migracaodedadosspringbatch.domain;

import org.apache.logging.log4j.util.Strings;

import java.sql.Date;

public record Pessoa(int id, String nome, String email, Date dataNascimento, int idade) {
    public boolean isValida() {
        return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null;
    }
}
