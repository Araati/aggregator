package com.ourcompany.aggregator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourcompany.aggregator.AggregatorApplication;
import com.ourcompany.aggregator.dao.PositionRepository;
import com.ourcompany.aggregator.dao.ResumeRepository;
import com.ourcompany.aggregator.dto.ResumeCreateDTO;
import com.ourcompany.aggregator.dto.ResumeUpdateDTO;
import com.ourcompany.aggregator.model.entity.PositionEntity;
import com.ourcompany.aggregator.model.entity.ResumeEntity;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {AggregatorApplication.class})
@WebAppConfiguration(value = "src/main/java/com/ourcompany/aggregator")
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ResumeServiceTest {
    @Autowired
    private WebApplicationContext context;
    private ObjectMapper mapper;

    private MockMvc mvc;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private ResumeRepository resumeRepository;


    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();
        resumeService = Mockito.mock(ResumeService.class);
    }

    @Test
    void create() throws Exception {
        String description = "test";
        List<Integer> tagList = new ArrayList<>();
        tagList.add(1);
        tagList.add(2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/resume")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new ResumeCreateDTO(description, List.of(1L, 2L), true)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tagList").value(tagList));
    }

    @Test
    void update() throws Exception {
        String description = "test";
        String newDescription = "test1";
        List<Integer> tagList = new ArrayList<>();
        tagList.add(3);
        tagList.add(2);
        ResumeEntity entity = new ResumeEntity(1, description, List.of(1L, 2L), false,false, LocalDateTime.now(), LocalDateTime.now());
        resumeRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/resume/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new ResumeUpdateDTO(newDescription, List.of(3L, 2L), false)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(newDescription))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tagList").value(tagList));
    }

    @Test
    void delete() throws Exception {
        String description = "test";
        ResumeEntity entity = new ResumeEntity(1, description, List.of(1L, 2L), false,false, LocalDateTime.now(), LocalDateTime.now());
        resumeRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/v1/resume/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        String description = "test";
        List<Integer> tagList = new ArrayList<>();
        tagList.add(1);
        tagList.add(2);
        ResumeEntity entity = new ResumeEntity(1, description, List.of(1L, 2L), false,false, LocalDateTime.now(), LocalDateTime.now());
        resumeRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/resume/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tagList").value(tagList));
    }

    @Test
    void findAllPositionsForResume() throws Exception {
        String position = "java";
        String skills = "test";
        String position1 = "java";
        String skills1 = "test1";
        String position2 = "c++";
        String skills2 = "test2";
        PositionEntity entity = new PositionEntity(1, 1, position,skills, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity);
        PositionEntity entity1 = new PositionEntity(2, 1, position1,skills1, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity1);
        PositionEntity entity2 = new PositionEntity(3, 1, position2,skills2, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity2);
        String description = "java";
        ResumeEntity entityResume = new ResumeEntity(1, description, List.of(1L, 2L), false,false, LocalDateTime.now(), LocalDateTime.now());
        entityResume = resumeRepository.save(entityResume);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/resume/position/" + entityResume.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].position").value("java"))
                .andExpect(jsonPath("$[1].position").value("java"));
    }
}