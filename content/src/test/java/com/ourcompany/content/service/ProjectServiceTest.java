package com.ourcompany.content.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ourcompany.content.AggregatorApplication;
import com.ourcompany.content.dao.ProjectRepository;
import com.ourcompany.content.dto.ProjectCreateDTO;
import com.ourcompany.content.dto.ProjectUpdateDTO;
import com.ourcompany.content.model.entity.ProjectEntity;
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
class ProjectServiceTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper mapper;

    private MockMvc mvc;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        projectService = Mockito.mock(ProjectService.class);
    }

    @Test
    void create() throws Exception {
        //init
        String description = "test";
        List<Integer> tagList = new ArrayList<>();
        tagList.add(1);
        tagList.add(2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/project")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new ProjectCreateDTO(description, List.of(1L, 2L))));
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
        ProjectEntity entity = new ProjectEntity(1, description, List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        projectRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/v1/project/" + entity.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(mapper.writeValueAsString(new ProjectUpdateDTO(newDescription, List.of(3L, 2L))));
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
        ProjectEntity entity = new ProjectEntity(1, description, List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        projectRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/v1/project/" + entity.getId())
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
        ProjectEntity entity = new ProjectEntity(1, description, List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        projectRepository.save(entity);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/project/" + entity.getId())
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
    void findAll() throws Exception {
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(1);
        list.add(2);
        list1.add(2);
        list1.add(3);
        list2.add(1);
        list2.add(2);
        ProjectEntity entity = new ProjectEntity(1, "test1", List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        ProjectEntity entity1 = new ProjectEntity(2, "test2", List.of(2L, 3L), false, LocalDateTime.now(), LocalDateTime.now());
        ProjectEntity entity2 = new ProjectEntity(3, "test3", List.of(1L, 2L), false, LocalDateTime.now(), LocalDateTime.now());
        projectRepository.save(entity);
        projectRepository.save(entity1);
        projectRepository.save(entity2);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/v1/project/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[0].description").value("test1"))
                .andExpect(jsonPath("$[1].description").value("test2"))
                .andExpect(jsonPath("$[2].description").value("test3"))
                .andExpect(jsonPath("$[0].tagList").value(list))
                .andExpect(jsonPath("$[1].tagList").value(list1))
                .andExpect(jsonPath("$[2].tagList").value(list2));
    }
}