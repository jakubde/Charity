package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.InstitutionDto;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private final InstitutionRepository institutionRepository;
    private final ModelMapper modelMapper;


    public InstitutionService(InstitutionRepository institutionRepository, ModelMapper modelMapper) {
        this.institutionRepository = institutionRepository;
        this.modelMapper = modelMapper;
    }

    public InstitutionDto entityToDto (Institution institution){
        return modelMapper.map(institution, InstitutionDto.class);
    }

    public Institution dtoToEntity (InstitutionDto institutionDto){
        return modelMapper.map(institutionDto, Institution.class);
    }


    public void saveInstitution(InstitutionDto institutionDto){
        institutionRepository.save(dtoToEntity(institutionDto));
    }

    public void updateInstitution(InstitutionDto institutionDto) {
        institutionRepository.save(dtoToEntity(institutionDto));
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

    public List<InstitutionDto> getList(){
        List<InstitutionDto> institutionDtos = new ArrayList<>();

        for (Institution institution : institutionRepository.findAll()){
            institutionDtos.add(entityToDto(institution));
        }
        return institutionDtos;
    }

    public InstitutionDto findById(Long id){
        return entityToDto(institutionRepository.findAllById(id));
    }

}
