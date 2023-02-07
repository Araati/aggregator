package com.ourcompany.aggregator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourcompany.aggregator.AggregatorApplication;
import com.ourcompany.aggregator.dao.PositionRepository;
import com.ourcompany.aggregator.dao.ProjectRepository;
import com.ourcompany.aggregator.dto.PositionCreateDTO;
import com.ourcompany.aggregator.dto.PositionUpdateDTO;
import com.ourcompany.aggregator.model.entity.PositionEntity;
import com.ourcompany.aggregator.model.entity.ProjectEntity;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = {AggregatorApplication.class})
@WebAppConfiguration(value = "src/main/java/com/ourcompany/aggregator")
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PositionServiceTest {

    @Autowired
    private WebApplicationContext context;
    private ObjectMapper mapper;

    private MockMvc mvc;
    @Autowired
    private PositionService positionService;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();
        positionService = Mockito.mock(PositionService.class);
    }

    @Test
    void create() throws Exception {
        //init
        ProjectEntity entity = new ProjectEntity(1, "test", List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        projectRepository.save(entity);
        String position = "test";
        String skills = "test";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/position")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new PositionCreateDTO(entity.getId(), position, skills)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectId").value(1))
                .andExpect(jsonPath("$.position").value(position))
                .andExpect(jsonPath("$.skills").value(skills));
    }

    @Test
    void update() throws Exception {
        String position = "test";
        String skills = "test";
        String newPosition = "test1";
        String newSkills = "test2";
        PositionEntity positionEntity = new PositionEntity(1, 1, position, skills, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(positionEntity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/position/" + positionEntity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new PositionUpdateDTO(newPosition, newSkills)));
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position").value(newPosition))
                .andExpect(jsonPath("$.skills").value(newSkills));
    }

    @Test
    void delete() throws Exception {
        String position = "test";
        String skills = "test";
        PositionEntity entity = new PositionEntity(1, 1, position,skills, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/v1/position/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        String position = "test";
        String skills = "test";
        PositionEntity entity = new PositionEntity(1, 1, position,skills, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/position/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position").value(position))
                .andExpect(jsonPath("$.skills").value(skills));
    }

    @Test
    void findByProjectId() throws Exception {
        String position = "test";
        String skills = "test";
        String position1 = "test1";
        String skills1 = "test1";
        String position2 = "test2";
        String skills2 = "test2";
        PositionEntity entity = new PositionEntity(1, 1, position,skills, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity);
        PositionEntity entity1 = new PositionEntity(2, 1, position1,skills1, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity1);
        PositionEntity entity2 = new PositionEntity(3, 1, position2,skills2, false, LocalDateTime.now(), LocalDateTime.now());
        positionRepository.save(entity2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/position/project/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[0].skills").value(skills))
                .andExpect(jsonPath("$[1].skills").value(skills1))
                .andExpect(jsonPath("$[2].skills").value(skills2))
                .andExpect(jsonPath("$[0].position").value(position))
                .andExpect(jsonPath("$[1].position").value(position1))
                .andExpect(jsonPath("$[2].position").value(position2));
    }
}