package com.ourcompany.aggregator.dao;

import com.ourcompany.aggregator.exception.ResourceNotFoundException;
import com.ourcompany.aggregator.model.entity.ResumeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResumeRepository extends CrudRepository<ResumeEntity, Long> {

    Optional<ResumeEntity> findByIdAndDeletedIsFalse(final long id);

    default ResumeEntity mustFindById(final long id) {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Resume", id));
    }

    default ResumeEntity mustFindByIdAndDeletedIsFalse(final long id) {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Resume", id));
    }
}
