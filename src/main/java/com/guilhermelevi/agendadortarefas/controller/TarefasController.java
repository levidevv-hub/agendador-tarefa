package com.guilhermelevi.agendadortarefas.controller;

import com.guilhermelevi.agendadortarefas.business.TarefaService;
import com.guilhermelevi.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefas(@RequestHeader("Authorization") String token,
                                                   @RequestBody TarefaDTO tarefaDTO) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

}
