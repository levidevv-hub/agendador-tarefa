package com.guilhermelevi.agendadortarefas.business.mapper;

import com.guilhermelevi.agendadortarefas.business.dto.TarefaDTO;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefasEntity paraTarefaEntity(TarefaDTO tarefasDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);

}
