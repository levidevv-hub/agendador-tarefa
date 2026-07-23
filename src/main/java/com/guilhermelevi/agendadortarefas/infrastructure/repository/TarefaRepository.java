package com.guilhermelevi.agendadortarefas.infrastructure.repository;

import com.guilhermelevi.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefasEntity, String> {
}
