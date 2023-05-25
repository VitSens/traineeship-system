package ru.coda.traineeship.configure;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.coda.traineeship.recruitment.model.Resume;
import ru.coda.traineeship.recruitment.model.dto.ResumeResponseDto;

@Configuration
public class ModelMapperConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    modelMapper.createTypeMap(Resume.class, ResumeResponseDto.class)
               .addMappings(new PropertyMap<Resume, ResumeResponseDto>() {
                 @Override
                 protected void configure() {
                   using(ctx -> generateFullName(
                       ((Resume) ctx.getSource()).getSurName(),
                       ((Resume) ctx.getSource()).getFirstName(),
                       ((Resume) ctx.getSource()).getMiddleName()
                   )).map(source, destination.getFullName());
                 }
               });

    return modelMapper;
  }

  private String generateFullName(String surName, String firstName, String middleName){
    return surName + " " + firstName + " " + middleName;
  }

}
