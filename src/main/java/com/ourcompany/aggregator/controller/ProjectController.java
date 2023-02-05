package com.ourcompany.aggregator.controller;

import com.ourcompany.aggregator.dto.ProjectCreateDTO;
import com.ourcompany.aggregator.dto.ProjectUpdateDTO;
import com.ourcompany.aggregator.facade.ProjectFacade;
import com.ourcompany.aggregator.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectFacade projectFacade;

    @PostMapping
    public Project create(@Valid @RequestBody final ProjectCreateDTO request) {
        return projectFacade.create(request);
    }

    @PostMapping("/{id}")
    public Project update(@RequestBody final ProjectUpdateDTO request,
                          @PathVariable final long id) {
        return projectFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        projectFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable final long id)    {
        return projectFacade.findById(id);
    }

    @GetMapping
    public List<Project> getAll() {
        return projectFacade.getAll();
    }

}