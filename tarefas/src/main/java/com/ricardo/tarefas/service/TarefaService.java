package com.ricardo.tarefas.service;

import com.ricardo.tarefas.dto.TarefaDTO;
import com.ricardo.tarefas.dto.TarefaMapper;
import com.ricardo.tarefas.exception.ExcecaoRegistroNaoEncontrado;
import com.ricardo.tarefas.repository.TarefaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public List<TarefaDTO> list() {
    return tarefaRepository.findAll()
            .stream()
            .map(tarefaMapper::toDTO)
            .collect(Collectors.toList());
}

    public TarefaDTO buscarTarefa(@NotNull @Positive Long id) {
        return tarefaRepository.findById(id).map(tarefaMapper::toDTO)
                .orElseThrow( () -> new ExcecaoRegistroNaoEncontrado(id));
    }

    public TarefaDTO create(@Valid @NotNull TarefaDTO tarefaDTO) {
        return tarefaMapper.toDTO(tarefaRepository.save(tarefaMapper.toEntity(tarefaDTO)));
    }

    public TarefaDTO atualizaTarefa(@NotNull @Positive Long id, @NotNull TarefaDTO tarefaDTO) {
        return tarefaRepository.findById(id)
                .map(registroEncontrado -> {
                    registroEncontrado.setResponsavel(tarefaDTO.responsavel);
                    registroEncontrado.setEtapa(tarefaMapper.convertTarefaValue(tarefaDTO.etapa));
                    registroEncontrado.setConteudo(tarefaDTO.conteudo);
                    return tarefaMapper.toDTO(tarefaRepository.save(registroEncontrado));
                }).orElseThrow(() -> new ExcecaoRegistroNaoEncontrado(id));
    }

    public void removeTarefa(@NotNull @Positive Long id) {
        tarefaRepository.delete(tarefaRepository.findById(id)
                .orElseThrow(() -> new ExcecaoRegistroNaoEncontrado(id)));
    }

}
