package com.guilhermelevi.agendadortarefas.infrastructure.repository;

import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.guilhermelevi.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicial,
                                                                        LocalDateTime dataFinal,
                                                                        StatusNotificacaoEnum statusNotificacaoEnum);

    List<TarefasEntity> findByEmailUsuario(String email);

}
