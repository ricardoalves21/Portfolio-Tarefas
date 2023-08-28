package com.ricardo.tarefas.dto;

import com.ricardo.tarefas.enums.Etapa;
import com.ricardo.tarefas.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public TarefaDTO toDTO(Tarefa tarefa) {

        if(tarefa == null) {
            return null;
        }

        return new TarefaDTO(tarefa.getId(), tarefa.getConteudo(), tarefa.getResponsavel(), tarefa.getEtapa().getValue());
    }

    public Tarefa toEntity(TarefaDTO tarefaDTO) {

        if(tarefaDTO == null) {
            return null;
        }

        Tarefa tarefa = new Tarefa();

        if(tarefaDTO.id != null) {
            tarefa.setId(tarefaDTO.id);
        }

        tarefa.setResponsavel(tarefaDTO.responsavel);
        tarefa.setConteudo(tarefaDTO.conteudo);
        tarefa.setEtapa(convertTarefaValue(tarefaDTO.etapa));
        return tarefa;
    }

    public Etapa convertTarefaValue(String value) {

        if(value == null) {
            return null;
        }

        return switch (value) {
            case "etapa1" -> Etapa.AFAZER;
            case "etapa2" -> Etapa.FAZENDO;
            case "etapa3" -> Etapa.FEITO;
            default -> throw new IllegalArgumentException("Etapa inválida: " + value);
        };
    }
}
