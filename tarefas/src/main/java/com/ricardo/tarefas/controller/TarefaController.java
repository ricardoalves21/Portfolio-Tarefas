package com.ricardo.tarefas.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;
}
