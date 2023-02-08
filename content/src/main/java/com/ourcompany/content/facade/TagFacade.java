package com.ourcompany.content.facade;

import com.ourcompany.content.dto.TagCreateDTO;
import com.ourcompany.content.dto.TagUpdateDTO;
import model.Tag;
import com.ourcompany.content.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Tag> findAll() {
        return tagService.findAll();
    }
    
}
