package com.ourcompany.aggregator.service;

import com.ourcompany.aggregator.dao.PositionRepository;
import com.ourcompany.aggregator.dto.PositionCreateDTO;
import com.ourcompany.aggregator.dto.PositionDTO;
import com.ourcompany.aggregator.dto.PositionUpdateDTO;
import com.ourcompany.aggregator.model.Position;
import com.ourcompany.aggregator.model.entity.PositionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    public Position create(final PositionCreateDTO request)   {
        PositionEntity entity = new PositionEntity(request);
        positionRepository.save(entity);
        log.info("Position with {} id created", entity.getId());
        return new PositionDTO(entity);
    }

    public Position update(final PositionUpdateDTO request, final long id)   {
        PositionEntity entity = positionRepository.mustFindById(id);

        //В случае добавление новых полей в position необходимо расширить код ниже
        entity = entity
                .withPosition(request.getPosition());
        positionRepository.save(entity);
        log.info("Position with {} id updated", entity.getId());

        //Важный момент: updatedAt возвращается старый, ибо это поле обновляется при сохранении
        //Если это будет критично, можно повесить .withUpdatedAt(LocalDateTime.now()), это не повлияет на сохраняемое entity, но даст желаемый результат
        return new PositionDTO(entity);
    }

    public void delete(final long id)   {
        positionRepository.save(positionRepository.mustFindById(id).withDeleted(true));
        log.info("Position with {} id deleted", id);
    }

    public Position findById(final long id)  {
        return new PositionDTO(positionRepository.mustFindByIdAndDeletedIsFalse(id));
    }

}
