package com.guilhermelevi.agendadortarefas.business.mapper;

import com.guilhermelevi.agendadortarefas.business.dto.TarefasDTORecord;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateDeTarefas(TarefasDTORecord tarefaDTORecord, @MappingTarget TarefasEntity tarefasEntity);

}
