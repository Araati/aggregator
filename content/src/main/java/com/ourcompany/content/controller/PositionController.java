package com.ourcompany.content.controller;

import com.ourcompany.content.dto.PositionCreateDTO;
import com.ourcompany.content.dto.PositionUpdateDTO;
import com.ourcompany.content.facade.PositionFacade;
import model.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionFacade positionFacade;

    @PostMapping
    public Position create(@Valid @RequestBody final PositionCreateDTO request) {
        return positionFacade.create(request);
    }

    @PatchMapping("/{id}")
    public Position update(@RequestBody final PositionUpdateDTO request,
                           @PathVariable final long id) {
        return positionFacade.update(request, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final long id) {
        positionFacade.delete(id);
    }

    @GetMapping("/{id}")
    public Position findById(@PathVariable final long id)    {
        return positionFacade.findById(id);
    }

    @GetMapping("/project/{id}")
    public List<Position> findAllByProjectId(@PathVariable final long id) {
        return positionFacade.findAllByProjectId(id);
    }

}
