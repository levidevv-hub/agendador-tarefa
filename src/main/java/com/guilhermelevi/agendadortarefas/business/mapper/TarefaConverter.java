package com.guilhermelevi.agendadortarefas.business.mapper;

import com.guilhermelevi.agendadortarefas.business.dto.TarefaDTO;
import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefaEntity(TarefaDTO tarefasDTO);

    TarefaDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaDeTaarefasEntity(List<TarefaDTO> tarefaDTO);

    List<TarefaDTO> paraListaDeTarefasDto(List<TarefasEntity> tarefasEntity);

}
