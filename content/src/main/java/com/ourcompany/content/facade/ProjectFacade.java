package com.ourcompany.content.facade;

import com.ourcompany.content.dto.ProjectCreateDTO;
import com.ourcompany.content.dto.ProjectUpdateDTO;
import com.ourcompany.content.model.Project;
import com.ourcompany.content.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;

    public Project create(final ProjectCreateDTO request)    {
        return projectService.create(request);
    }

    public Project update(final ProjectUpdateDTO request, final long id)    {
        return projectService.update(request, id);
    }

    public void delete(final long id)   {
        projectService.delete(id);
    }

    public Project findById(final long id)   {
        return projectService.findById(id);
    }

    public List<Project> findAll() {
        return projectService.findAll();
    }
}
