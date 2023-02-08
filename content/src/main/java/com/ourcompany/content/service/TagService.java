package com.ourcompany.content.service;

import com.ourcompany.content.dao.TagRepository;
import com.ourcompany.content.dto.TagCreateDTO;
import com.ourcompany.content.dto.TagDTO;
import com.ourcompany.content.dto.TagUpdateDTO;
import model.Tag;
import com.ourcompany.content.model.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag create(final TagCreateDTO request)   {
        TagEntity entity = new TagEntity(request);
        tagRepository.save(entity);
        log.info("Tag with {} id created", entity.getId());
        return new TagDTO(entity);
    }

    public Tag update(final TagUpdateDTO request, final long id)   {
        TagEntity entity = tagRepository.mustFindById(id);

        entity = entity
                .withLabel(request.getLabel().orElse(entity.getLabel()))
                .withType(request.getType().orElse(entity.getType()));
        tagRepository.save(entity);
        log.info("Tag with {} id updated", entity.getId());

        //Важный момент: updatedAt возвращается старый, ибо это поле обновляется при сохранении
        //Если это будет критично, можно повесить .withUpdatedAt(LocalDateTime.now()), это не повлияет на сохраняемое entity, но даст желаемый результат
        return new TagDTO(entity);
    }

    public void delete(final long id)   {
        tagRepository.save(tagRepository.mustFindById(id).withDeleted(true));
        log.info("Tag with {} id deleted", id);
    }

    public Tag findById(final long id)  {
        return new TagDTO(tagRepository.mustFindByIdAndDeletedIsFalse(id));
    }

    public List<Tag> findAll() {
        List<TagEntity> tagEntities = new ArrayList<>();
        tagRepository.findAllByDeletedIsFalse().forEach(tagEntities::add);
        return tagEntities.stream().map(TagDTO::new).collect(Collectors.toList());
    }
}
