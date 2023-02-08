package com.ourcompany.content.facade;

import com.ourcompany.content.dto.PositionCreateDTO;
import com.ourcompany.content.dto.PositionUpdateDTO;
import model.Position;
import com.ourcompany.content.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionFacade {

    private final PositionService positionService;

    public Position create(final PositionCreateDTO request)    {
        return positionService.create(request);
    }

    public Position update(final PositionUpdateDTO request, final long id)    {
        return positionService.update(request, id);
    }

    public void delete(final long id)   {
        positionService.delete(id);
    }

    public Position findById(final long id)   {
        return positionService.findById(id);
    }

    public List<Position> findAllByProjectId(final long id) {
        return positionService.findAllByProjectId(id);
    }

}
