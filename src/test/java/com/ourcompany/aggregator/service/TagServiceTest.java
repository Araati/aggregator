package com.ourcompany.aggregator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourcompany.aggregator.AggregatorApplication;
import com.ourcompany.aggregator.dao.TagRepository;
import com.ourcompany.aggregator.dto.TagCreateDTO;
import com.ourcompany.aggregator.dto.TagUpdateDTO;
import com.ourcompany.aggregator.model.entity.TagEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ContextConfiguration(classes = {AggregatorApplication.class})
@WebAppConfiguration(value = "src/main/java/com/ourcompany/aggregator")
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TagServiceTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper mapper;

    private MockMvc mvc;
    @Autowired
    private TagService tagService;
    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        tagService = Mockito.mock(TagService.class);
    }

    @Test
    void create() throws Exception {
        //init
        String label = "test";
        String type = "test";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/tag")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new TagCreateDTO(label, type)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label").value(label))
                .andExpect(jsonPath("$.type").value(type));
    }

    @Test
    void update() throws Exception {
        String label = "test";
        String type = "test";
        String newLabel = "test1";
        String newType = "test1";
        TagEntity entity = new TagEntity(1, label, type, false, LocalDateTime.now(), LocalDateTime.now());
        tagRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/tag/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new TagUpdateDTO(newLabel, newType)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label").value(newLabel))
                .andExpect(jsonPath("$.type").value(newType));
    }

    @Test
    void delete() throws Exception {
        String label = "test";
        String type = "test";
        TagEntity entity = new TagEntity(1, label, type, false, LocalDateTime.now(), LocalDateTime.now());
        tagRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/v1/tag/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        String label = "test";
        String type = "test";
        TagEntity entity = new TagEntity(1, label, type, false, LocalDateTime.now(), LocalDateTime.now());
        tagRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/tag/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.label").value(label))
                .andExpect(jsonPath("$.type").value(type));
    }

    @Test
    void findAll() throws Exception {
        String label = "test";
        String type = "test";
        String label1 = "test1";
        String type1 = "test1";
        String label2 = "test2";
        String type2 = "test2";
        TagEntity entity = new TagEntity(1, label, type, false, LocalDateTime.now(), LocalDateTime.now());
        TagEntity entity1 = new TagEntity(2, label1, type1, false, LocalDateTime.now(), LocalDateTime.now());
        TagEntity entity2 = new TagEntity(3, label2, type2, false, LocalDateTime.now(), LocalDateTime.now());
        tagRepository.save(entity);
        tagRepository.save(entity1);
        tagRepository.save(entity2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/tag/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[0].label").value(label))
                .andExpect(jsonPath("$[1].label").value(label1))
                .andExpect(jsonPath("$[2].label").value(label2))
                .andExpect(jsonPath("$[0].type").value(type))
                .andExpect(jsonPath("$[1].type").value(type1))
                .andExpect(jsonPath("$[2].type").value(type2));
    }
}