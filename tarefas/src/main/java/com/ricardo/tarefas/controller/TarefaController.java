package com.ricardo.tarefas.controller;

import com.ricardo.tarefas.dto.TarefaDTO;
import com.ricardo.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public List<TarefaDTO> list() {
        return tarefaService.list();
    }

    @GetMapping("/{id}")
    public TarefaDTO buscarTarefa(@PathVariable @NotNull @Positive Long id) {
        return tarefaService.buscarTarefa(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TarefaDTO create(@RequestBody @Valid @NotNull TarefaDTO tarefaDTO) {
        return tarefaService.create(tarefaDTO);
    }

    @PutMapping("/{id}")
    public TarefaDTO atualizaTarefa(@PathVariable @NotNull @Positive Long id,
                                    @RequestBody @Valid @NotNull TarefaDTO tarefaDTO) {
        return tarefaService.atualizaTarefa(id, tarefaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void removeTarefa(@PathVariable @NotNull @Positive Long id) {
        tarefaService.removeTarefa(id);
    }

}
