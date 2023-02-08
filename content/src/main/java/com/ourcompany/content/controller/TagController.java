package com.ourcompany.content.controller;

import com.ourcompany.content.dto.TagCreateDTO;
import com.ourcompany.content.dto.TagUpdateDTO;
import com.ourcompany.content.facade.TagFacade;
import model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public List<Tag> findAll() {
        return tagFacade.findAll();
    }

}
