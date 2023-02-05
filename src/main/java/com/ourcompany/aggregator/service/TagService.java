package com.ourcompany.aggregator.service;

import com.ourcompany.aggregator.dao.TagRepository;
import com.ourcompany.aggregator.dto.TagCreateDTO;
import com.ourcompany.aggregator.dto.TagDTO;
import com.ourcompany.aggregator.dto.TagUpdateDTO;
import com.ourcompany.aggregator.model.Tag;
import com.ourcompany.aggregator.model.entity.TagEntity;
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

        //В случае добавление новых полей в tag необходимо расширить код ниже
        entity = entity
                .withLabel(request.getLabel());
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
