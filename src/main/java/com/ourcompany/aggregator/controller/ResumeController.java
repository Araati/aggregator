package com.ourcompany.aggregator.controller;

import com.ourcompany.aggregator.dto.ResumeCreateDTO;
import com.ourcompany.aggregator.dto.ResumeUpdateDTO;
import com.ourcompany.aggregator.facade.ResumeFacade;
import com.ourcompany.aggregator.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeFacade resumeFacade;

    @PostMapping
    public Resume create(@Valid @RequestBody final ResumeCreateDTO request) { return resumeFacade.create(request); }

    @PostMapping("/{id}")
    public Resume update(@RequestBody final ResumeUpdateDTO request, @PathVariable final long id) { return resumeFacade.update(request, id); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) { resumeFacade.delete(id); }

    @GetMapping("/{id}")
    public Resume findById(@PathVariable final long id) { return resumeFacade.findById(id); }

}
