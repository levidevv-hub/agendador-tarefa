package com.guilhermelevi.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guilhermelevi.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record TarefasDTORecord(String id,
                               String nomeTarefa,
                               String descricao,
                               LocalDateTime dataCriacao,
                               @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyy HH:mm:ss")
                               LocalDateTime dataEvento,
                               String emailUsuario,
                               LocalDateTime dataAlteracao,
                               StatusNotificacaoEnum statusNotificacaoEnum) {
}
