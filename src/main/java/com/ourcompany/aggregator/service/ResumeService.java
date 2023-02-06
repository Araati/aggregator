package com.ourcompany.aggregator.service;

import com.ourcompany.aggregator.dao.ResumeRepository;
import com.ourcompany.aggregator.dto.ResumeCreateDTO;
import com.ourcompany.aggregator.dto.ResumeDTO;
import com.ourcompany.aggregator.dto.ResumeUpdateDTO;
import com.ourcompany.aggregator.model.Resume;
import com.ourcompany.aggregator.model.entity.ResumeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

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

}
