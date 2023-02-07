package com.ourcompany.aggregator.dao;

import com.ourcompany.aggregator.exception.ResourceNotFoundException;
import com.ourcompany.aggregator.model.entity.PositionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends CrudRepository<PositionEntity, Long> {
    List<PositionEntity> findAllByProjectIdAndDeletedIsFalse(final long id);

    Optional<PositionEntity> findByIdAndDeletedIsFalse(final long id);

    default PositionEntity mustFindById(final long id)   {
        return findById(id).orElseThrow(() -> new ResourceNotFoundException("Position", id));
    }

    default PositionEntity mustFindByIdAndDeletedIsFalse(final long id)   {
        return findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Position", id));
    }

    List<PositionEntity> findAllByDeletedIsFalse();

}
