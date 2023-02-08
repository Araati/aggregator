package com.ourcompany.content.service;

import com.ourcompany.content.dao.PositionRepository;
import com.ourcompany.content.dao.ResumeRepository;
import com.ourcompany.content.dto.PositionDTO;
import com.ourcompany.content.dto.ResumeCreateDTO;
import com.ourcompany.content.dto.ResumeDTO;
import com.ourcompany.content.dto.ResumeUpdateDTO;
import com.ourcompany.content.model.Position;
import com.ourcompany.content.model.Resume;
import com.ourcompany.content.model.entity.ResumeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final PositionRepository positionRepository;

    public Resume create(final ResumeCreateDTO request) {
        ResumeEntity entity = new ResumeEntity(request);
        resumeRepository.save(entity);
        log.info("Resume with {} id created", entity.getId());
        return new ResumeDTO(entity);
    }

    public Resume update(final ResumeUpdateDTO request, final long id) {
        ResumeEntity entity = resumeRepository.mustFindById(id);

        entity = entity
                .withDescription(request.getDescription().orElse(entity.getDescription()))
                .withTagList(request.getTagList().orElse(entity.getTagList()))
                .withFree(request.isFree().orElse(entity.isFree()));
        resumeRepository.save(entity);
        log.info("Resume with {} id updated", entity.getId());

        //Важный момент: updatedAt возвращается старый, ибо это поле обновляется при сохранении
        //Если это будет критично, можно повесить .withUpdatedAt(LocalDateTime.now()), это не повлияет на сохраняемое entity, но даст желаемый результат
        return new ResumeDTO(entity);
    }

    public void delete(final long id) {
        resumeRepository.save(resumeRepository.mustFindById(id).withDeleted(true));
        log.info("Resume with {} id deleted", id);
    }

    public Resume findById(final long id) {
        return new ResumeDTO(resumeRepository.mustFindByIdAndDeletedIsFalse(id));
    }

    // TODO: 06.02.2023 этот метод позволяет найти подходящие позиции для выбранного резюме, но его нужно доработать и протестить
    public List<Position> findAllPositionsForResume(final long id) {
        List<Position> allPositions = positionRepository.findAllByDeletedIsFalse().stream()
                                        .map(PositionDTO::new)
                                        .collect(Collectors.toList());
        List<Position> needPositions = new ArrayList<>();
        ResumeEntity entity = resumeRepository.mustFindById(id);
        for (Position position : allPositions) {
            String descriptionPosition = position.getPosition();
            String description = entity.getDescription();
            if (descriptionPosition.contains(description)) {
                needPositions.add(position);
            }
        } return needPositions;
    }
}
