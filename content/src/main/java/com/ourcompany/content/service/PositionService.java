package com.ourcompany.content.service;

import com.ourcompany.content.dao.PositionRepository;
import com.ourcompany.content.dao.ProjectRepository;
import com.ourcompany.content.dto.PositionCreateDTO;
import com.ourcompany.content.dto.PositionDTO;
import com.ourcompany.content.dto.PositionUpdateDTO;
import model.Position;
import com.ourcompany.content.model.entity.PositionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;
    private final ProjectRepository projectRepository;

    public Position create(final PositionCreateDTO request)   {
        projectRepository.mustFindById(request.getProjectId());
        PositionEntity entity = new PositionEntity(request);
        positionRepository.save(entity);
        log.info("Position with {} id created", entity.getId());
        return new PositionDTO(entity);
    }

    public Position update(final PositionUpdateDTO request, final long id)   {
        PositionEntity entity = positionRepository.mustFindById(id);

        entity = entity
                .withPosition(request.getPosition().orElse(entity.getPosition()))
                .withSkills(request.getSkills().orElse(entity.getSkills()));
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

    public List<Position> findAllByProjectId(final long id) {
        return positionRepository.findAllByProjectIdAndDeletedIsFalse(id).stream()
                .map(PositionDTO::new)
                .collect(Collectors.toList());
    }

}
