package com.ourcompany.aggregator.facade;

import com.ourcompany.aggregator.dto.ProjectCreateDTO;
import com.ourcompany.aggregator.dto.ProjectUpdateDTO;
import com.ourcompany.aggregator.model.Project;
import com.ourcompany.aggregator.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
