package com.guilhermelevi.agendadortarefas.controller;

import com.guilhermelevi.agendadortarefas.business.TarefaService;
import com.guilhermelevi.agendadortarefas.business.dto.TarefasDTORecord;
import com.guilhermelevi.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefasDTORecord> gravarTarefas(@RequestHeader("Authorization") String token,
                                                          @RequestBody TarefasDTORecord tarefaDTORecord) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTORecord));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTORecord>> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTORecord>> buscaTarefaPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTORecord> alteraStatusDeNotificacao(
            @RequestParam("status")StatusNotificacaoEnum statusNotificacaoEnum,
            @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatus(statusNotificacaoEnum,id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTORecord> updateTarefas(@RequestBody TarefasDTORecord tarefaDTORecord,
                                                   @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateDeTarefas(tarefaDTORecord, id));
    }


}
