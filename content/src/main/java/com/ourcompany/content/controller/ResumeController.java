package com.ourcompany.content.controller;

import com.ourcompany.content.dto.ResumeCreateDTO;
import com.ourcompany.content.dto.ResumeUpdateDTO;
import com.ourcompany.content.facade.ResumeFacade;
import com.ourcompany.content.model.Position;
import com.ourcompany.content.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeFacade resumeFacade;

    @PostMapping
    public Resume create(@Valid @RequestBody final ResumeCreateDTO request) {
        return resumeFacade.create(request);
    }

    @PostMapping("/{id}")
    public Resume update(@RequestBody final ResumeUpdateDTO request,
                         @PathVariable final long id)   {
        return resumeFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        resumeFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Resume findById(@PathVariable final long id) {
        return resumeFacade.findById(id);
    }

    @GetMapping("/position/{id}")
    public List<Position> findAllPositionsForResume(@PathVariable final long id)    {
        return resumeFacade.findAllPositionsForResume(id);
    }

}
