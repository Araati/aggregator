package com.ourcompany.aggregator.controller;

import com.ourcompany.aggregator.dto.TagCreateDTO;
import com.ourcompany.aggregator.dto.TagUpdateDTO;
import com.ourcompany.aggregator.facade.TagFacade;
import com.ourcompany.aggregator.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagFacade tagFacade;

    @PostMapping
    public Tag create(@Valid @RequestBody final TagCreateDTO request) {
        return tagFacade.create(request);
    }

    @PostMapping("/{id}")
    public Tag update(@RequestBody final TagUpdateDTO request,
                          @PathVariable final long id) {
        return tagFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        tagFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Tag findById(@PathVariable final long id)    {
        return tagFacade.findById(id);
    }

}