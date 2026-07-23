package com.guilhermelevi.agendadortarefas.business;

import com.guilhermelevi.agendadortarefas.business.dto.TarefaDTO;
import com.guilhermelevi.agendadortarefas.business.mapper.TarefaConverter;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.guilhermelevi.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.guilhermelevi.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.guilhermelevi.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefasDTO) {
        String email = jwtUtil.extractUsername(token.substring(7));
        tarefasDTO.setEmailUsuario(email);
        tarefasDTO.setDataAlteracao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity tarefasEntity = tarefaConverter.paraTarefaEntity(tarefasDTO);
        return tarefaConverter.paraTarefaDTO(tarefasRepository.save(tarefasEntity));
    }

}
