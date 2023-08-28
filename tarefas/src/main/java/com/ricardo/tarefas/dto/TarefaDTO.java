package com.ricardo.tarefas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class TarefaDTO {

    @JsonProperty("id")
    public Long id;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 200)
    public String conteudo;

    @NotBlank
    @NotNull
    @Length(min = 5, max = 100)
    public String responsavel;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "etapa1|etapa2|etapa3")
    public String etapa;

    public TarefaDTO(Long id, String conteudo, String responsavel, String etapa) {
        this.id = id;
        this.conteudo = conteudo;
        this.responsavel = responsavel;
        this.etapa = etapa;
    }
}
