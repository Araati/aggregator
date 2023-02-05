package com.ourcompany.aggregator.facade;

import com.ourcompany.aggregator.dto.TagCreateDTO;
import com.ourcompany.aggregator.dto.TagUpdateDTO;
import com.ourcompany.aggregator.model.Tag;
import com.ourcompany.aggregator.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagFacade {

    private final TagService tagService;

    public Tag create(final TagCreateDTO request)    {
        return tagService.create(request);
    }

    public Tag update(final TagUpdateDTO request, final long id)    {
        return tagService.update(request, id);
    }

    public void delete(final long id)   {
        tagService.delete(id);
    }

    public Tag findById(final long id)   {
        return tagService.findById(id);
    }
    
}
