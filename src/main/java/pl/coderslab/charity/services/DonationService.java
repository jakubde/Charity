package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.CategoryDto;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.repositories.DonationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final ModelMapper modelMapper;

    public DonationService(DonationRepository donationRepository, ModelMapper modelMapper) {
        this.donationRepository = donationRepository;
        this.modelMapper = modelMapper;
    }


    public DonationDto entityToDto (Donation donation){
        return modelMapper.map(donation, DonationDto.class);
    }

    public Donation dtoToEntity (DonationDto donationDto){
        return modelMapper.map(donationDto, Donation.class);
    }


    public DonationDto saveDonation(DonationDto donationDto) {
        return entityToDto(donationRepository.saveAndFlush(dtoToEntity(donationDto)));
    }

    public void save(DonationDto donationDto, List<CategoryDto> categoryDtos){
        for(CategoryDto categoryDto: categoryDtos){
            donationRepository.saveDonationIdCategoryId(donationDto.getId(), categoryDto.getId());
            System.out.println("donationId = " + donationDto.getId() + " | categoryId = " + categoryDto.getId());
        }
    }


    public void updateDonation(DonationDto donationDto) {
        donationRepository.save(dtoToEntity(donationDto));
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    public List<DonationDto> getList(){
        List<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donationRepository.findAll()){
            donationDtos.add(entityToDto(donation));
        }
        return donationDtos;
    }

    public Integer donationSum(){
        return donationRepository.sumAllDonations();
    }

    public int distinctInstitutionsCount(){
        return donationRepository.countAllDistinctInstitutions();
    }



}
