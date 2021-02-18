package pl.coderslab.charity.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.InstitutionRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.context.i18n.LocaleContextHolder;

@Service
@Transactional
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final ObjectMapper objectMapper;


    public InstitutionService(InstitutionRepository institutionRepository, ObjectMapper objectMapper) {
        this.institutionRepository = institutionRepository;
        this.objectMapper = objectMapper;
    }

    public void saveInstitution(InstitutionDto institutionDto) {
        institutionRepository.save(objectMapper.convert(institutionDto, Institution.class));
    }

    public void updateInstitution(InstitutionDto institutionDto) {
        Institution institution = institutionRepository.findAllById(institutionDto.getId());
        institution.setName(institutionDto.getName());
        institution.setDescription(institutionDto.getDescription());
        institution.setNameEng(institutionDto.getNameEng());
        institution.setDescriptionEng(institutionDto.getDescriptionEng());
        institutionRepository.save(institution);
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    public List<InstitutionDto> getList() {
        return objectMapper.convertAll(institutionRepository.findAll(), InstitutionDto.class);
    }

    public InstitutionDto findById(Long id) {
        return objectMapper.convert(institutionRepository.findAllById(id), InstitutionDto.class);
    }
    
    public List<List<String>> getInstitutionNameAndDescriptionInCorrespondingLanguage(){
        List<List<String>> listOfNameAndDescriptionStringPairs = new ArrayList<>();
        Locale locale = LocaleContextHolder.getLocale();
        String language = locale.getLanguage();
        List<InstitutionDto> institutionDtoList = getList();
            
        for(InstitutionDto institutionDto : institutionDtoList){
                List<String> nameAndDescriptionStringPair = new ArrayList<>();
                nameAndDescriptionStringPair.add(language.equals("pl") ? institutionDto.getName() : institutionDto.getNameEng());
                nameAndDescriptionStringPair.add(language.equals("pl") ? institutionDto.getDescription() : institutionDto.getDescriptionEng());
                listOfNameAndDescriptionStringPairs.add(nameAndDescriptionStringPair);
        }
        
        return listOfNameAndDescriptionStringPairs;
    }
    

}

