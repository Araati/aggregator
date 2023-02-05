package com.ourcompany.aggregator.service;

import com.ourcompany.aggregator.dao.PositionRepository;
import com.ourcompany.aggregator.dao.ProjectRepository;
import com.ourcompany.aggregator.dto.ProjectCreateDTO;
import com.ourcompany.aggregator.dto.ProjectDTO;
import com.ourcompany.aggregator.dto.ProjectUpdateDTO;
import com.ourcompany.aggregator.model.Project;
import com.ourcompany.aggregator.model.entity.ProjectEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PositionRepository positionRepository;

    public Project create(final ProjectCreateDTO request)   {
        ProjectEntity entity = new ProjectEntity(request);
        projectRepository.save(entity);
        log.info("Project with {} id created", entity.getId());
        return new ProjectDTO(entity);
    }

    public Project update(final ProjectUpdateDTO request, final long id)   {
        ProjectEntity entity = projectRepository.mustFindById(id);

        //В случае добавление новых полей в Project необходимо расширить код ниже
        entity = entity
                .withDescription(request.getDescription());
        projectRepository.save(entity);
        log.info("Project with {} id updated", entity.getId());

        //Важный момент: updatedAt возвращается старый, ибо это поле обновляется при сохранении
        //Если это будет критично, можно повесить .withUpdatedAt(LocalDateTime.now()), это не повлияет на сохраняемое entity, но даст желаемый результат
        return new ProjectDTO(entity);
    }

    public void delete(final long id)   {
        projectRepository.save(projectRepository.mustFindById(id).withDeleted(true));
        positionRepository.saveAll(positionRepository.findAllByProjectIdAndDeletedIsFalse(id).stream()
                                    .map(positionEntity -> positionEntity.withDeleted(true))
                                    .collect(Collectors.toList()));
        log.info("Project with {} id deleted", id);
    }

    public Project findById(final long id)  {
        return new ProjectDTO(projectRepository.mustFindByIdAndDeletedIsFalse(id));
    }

    public List<Project> getAll() {
        return projectRepository.findAllByDeletedIsFalse().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }
}
