package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.InstitutionRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final ObjectMapper objectMapper;


    public InstitutionService(InstitutionRepository institutionRepository, ObjectMapper objectMapper) {
        this.institutionRepository = institutionRepository;
        this.objectMapper = objectMapper;
    }

    public void saveInstitution(InstitutionDto institutionDto){
        institutionRepository.save(objectMapper.convert(institutionDto, Institution.class));
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    public List<InstitutionDto> getList(){
        return objectMapper.convertAll(institutionRepository.findAll(), InstitutionDto.class);
    }

    public InstitutionDto findById(Long id){
        return objectMapper.convert(institutionRepository.findAllById(id), InstitutionDto.class);
    }

}

