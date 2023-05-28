package ru.coda.traineeship.recruitment.controller;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.recruitment.model.ResumeVacancy;
import ru.coda.traineeship.recruitment.model.dto.ResumeRequestDto;
import ru.coda.traineeship.recruitment.model.dto.ResumeResponseDto;
import ru.coda.traineeship.recruitment.repository.ResumeVacancyRepository;
import ru.coda.traineeship.recruitment.service.ResumeService;
import ru.coda.traineeship.vacancy.model.Vacancy;
import ru.coda.traineeship.vacancy.model.dto.VacancyShortResponseDto;
import ru.coda.traineeship.vacancy.service.VacancyService;

@RestController
@RequestMapping("/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

  private final ModelMapper modelMapper;
  private final ResumeService resumeService;
  private final ResumeVacancyRepository resumeVacancyRepository;

  @GetMapping
  public List<ResumeResponseDto> getAllResumes(@RequestParam(required = false) String resume) {
    return resumeService.findAll(resume)
                        .stream()
                        .map(r -> modelMapper.map(r, ResumeResponseDto.class))
                        .collect(Collectors.toList());
  }

  @GetMapping("/{userId}")
  public List<ResumeResponseDto> getAllResumesByUserId(@PathVariable String userId) {
    return resumeService.findAllByUserId(userId)
                        .stream()
                        .map(r -> modelMapper.map(r, ResumeResponseDto.class))
                        .collect(Collectors.toList());
  }

  @GetMapping("/{userId}/vacancy")
  public List<VacancyShortResponseDto> getAllVacancySetByUserId(@PathVariable String userId) {
    List<Vacancy> vacancyStream = resumeService.findAllByUserId(userId)
                                                 .stream()
                                                 .map(r -> {
                                                   ResumeVacancy resume = resumeVacancyRepository.findByResumeId(r.getId());
                                                   return resume.getVacancy();
                                                 })
                                                 .collect(Collectors.toList());

    return vacancyStream.isEmpty() ? Collections.EMPTY_LIST : vacancyStream.stream()
        .map(r -> modelMapper.map(r, VacancyShortResponseDto.class))
        .collect(Collectors.toList());
  }

  @GetMapping("/{userId}/{resumeId}")
  public ResponseEntity<ResumeResponseDto> getResumeByUserId(@PathVariable String userId, @PathVariable Integer resumeId) {
    Resume resume = resumeService.findByUserIdAndResumeId(userId, resumeId);
    return resume != null ? ResponseEntity.ok(modelMapper.map(resume, ResumeResponseDto.class)) : ResponseEntity.notFound().build();
  }

  @GetMapping("/location")
  public List<String> getAllResumesByLocation(@RequestParam(value = "q") String location) {
    return resumeService.findAllByLocation(location);
  }

  @GetMapping("/specialization")
  public List<String> getAllResumesBySpecialization(@RequestParam(value = "q") String specialization) {
    return resumeService.findAllBySpecialization(specialization);
  }

  @PostMapping("/{userId}")
  public Resume createResume(
      @PathVariable String userId,
      @RequestBody ResumeRequestDto resume,
      HttpServletRequest request
  ) {
    return resumeService.createResume(userId, modelMapper.map(resume, Resume.class), request);
  }

  @PostMapping("/{resumeId}/submit/{vacancyId}")
  public String submitToVacancy(@PathVariable Integer resumeId, @PathVariable Integer vacancyId) {
    return resumeService.submitResume(resumeId, vacancyId);
  }

}
