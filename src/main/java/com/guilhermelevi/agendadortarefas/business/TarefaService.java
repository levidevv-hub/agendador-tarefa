package com.guilhermelevi.agendadortarefas.business;

import com.guilhermelevi.agendadortarefas.business.dto.TarefasDTORecord;
import com.guilhermelevi.agendadortarefas.business.mapper.TarefaConverter;
import com.guilhermelevi.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.guilhermelevi.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.guilhermelevi.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.guilhermelevi.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.guilhermelevi.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefasRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTORecord gravarTarefa(String token, TarefasDTORecord dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefasDTORecord dtoRecord = new TarefasDTORecord(null, dto.nomeTarefa(), dto.descricao(),
                LocalDateTime.now(), dto.dataEvento(),
                email, null, StatusNotificacaoEnum.PENDENTE
        );
        TarefasEntity tarefasEntity = tarefaConverter.paraTarefaEntity(dtoRecord);
        return tarefaConverter.paraTarefaDTORecord(tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial
            , LocalDateTime dataFinal) {
        return tarefaConverter.paraListaDeTarefasDto(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial,
                        dataFinal,
                        StatusNotificacaoEnum.PENDENTE)
        );
    }

    public List<TarefasDTORecord> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaDeTarefasDto(
                tarefasRepository.findByEmailUsuario(email)
        );
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(("Error ao deletar tarefas por id "
                    + id
                    + " "
                    + e.getCause()));
        }
    }

    public TarefasDTORecord alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada" + id));

            tarefasEntity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTORecord(tarefasRepository.save(tarefasEntity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da tarefa" + e.getCause());
        }
    }

    public TarefasDTORecord updateDeTarefas(TarefasDTORecord tarefaDTO, String id) {
        try {
            TarefasEntity tarefasEntity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada" + id));
            tarefaUpdateConverter.updateDeTarefas(tarefaDTO, tarefasEntity);
            return tarefaConverter.paraTarefaDTORecord(tarefasRepository.save(tarefasEntity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da tarefa" + e.getCause());
        }
    }

}
