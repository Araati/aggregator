package com.ourcompany.aggregator.facade;

import com.ourcompany.aggregator.dto.PositionCreateDTO;
import com.ourcompany.aggregator.dto.PositionUpdateDTO;
import com.ourcompany.aggregator.model.Position;
import com.ourcompany.aggregator.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
