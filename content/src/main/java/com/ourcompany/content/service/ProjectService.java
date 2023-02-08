package com.ourcompany.content.service;

import com.ourcompany.content.dao.PositionRepository;
import com.ourcompany.content.dao.ProjectRepository;
import com.ourcompany.content.dto.ProjectCreateDTO;
import com.ourcompany.content.dto.ProjectDTO;
import com.ourcompany.content.dto.ProjectUpdateDTO;
import model.Project;
import com.ourcompany.content.model.entity.ProjectEntity;
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
        // TODO: 05.02.2023 сделать проверку на существование тэгов
        ProjectEntity entity = projectRepository.mustFindById(id);

        entity = entity
                .withDescription(request.getDescription().orElse(entity.getDescription()))
                .withTagList(request.getTagList().orElse(entity.getTagList()));
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

    public List<Project> findAll() {
        return projectRepository.findAllByDeletedIsFalse().stream()
                .map(ProjectDTO::new)
                .collect(Collectors.toList());
    }

}
