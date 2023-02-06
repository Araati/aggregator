package com.ourcompany.aggregator.facade;

import com.ourcompany.aggregator.dto.ResumeCreateDTO;
import com.ourcompany.aggregator.dto.ResumeUpdateDTO;
import com.ourcompany.aggregator.model.Position;
import com.ourcompany.aggregator.model.Resume;
import com.ourcompany.aggregator.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeFacade {

    private final ResumeService resumeService;

    public Resume create(final ResumeCreateDTO request) { return resumeService.create(request); }

    public Resume update(final ResumeUpdateDTO request, final long id) { return resumeService.update(request, id); }

    public void delete(final long id) { resumeService.delete(id); }

    public Resume findById(final long id) { return resumeService.findById(id); }

    public List<Position> findAllPositionsForResume(final long id) {return resumeService.findAllPositionsForResume(id); }
}
