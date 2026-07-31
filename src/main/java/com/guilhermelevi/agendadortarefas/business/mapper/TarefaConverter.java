package com.guilhermelevi.agendadortarefas.business.mapper;

import com.guilhermelevi.agendadortarefas.business.dto.TarefasDTORecord;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefaEntity(TarefasDTORecord tarefasDTO);

    TarefasDTORecord paraTarefaDTORecord(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaDeTaarefasEntity(List<TarefasDTORecord> tarefaDTORecord);

    List<TarefasDTORecord> paraListaDeTarefasDto(List<TarefasEntity> tarefasEntity);

}
