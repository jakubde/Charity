package pl.coderslab.charity.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.repositories.CategoryRepository;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DonationService {

    private final CategoryRepository categoryRepository;
    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final ModelMapper modelMapper;

    public DonationService(CategoryRepository categoryRepository, DonationRepository donationRepository, InstitutionRepository institutionRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.modelMapper = modelMapper;
    }

    public DonationDto entityToDto (Donation donation){

        DonationDto donationDto = modelMapper.map(donation, DonationDto.class);

        List<Long> auxiliaryList = new ArrayList<>();
        for(Category category: donation.getCategories()){
            auxiliaryList.add(category.getId());
        }
        donationDto.setCategoryIds(auxiliaryList);

        donationDto.setInstitutionId(donation.getInstitution().getId());

        return donationDto;
    }

    public Donation dtoToEntity (DonationDto donationDto){

        Donation donation = modelMapper.map(donationDto, Donation.class);

        List<Category> auxiliaryList = new ArrayList<>();
        for (Long categoryId: donationDto.getCategoryIds()) {
            auxiliaryList.add(categoryRepository.findAllById(categoryId));
        }
        donation.setCategories(auxiliaryList);

        donation.setInstitution(institutionRepository.findAllById(donationDto.getInstitutionId()));

        return donation;
    }

    public void saveDonation(DonationDto donationDto){
        donationRepository.save(dtoToEntity(donationDto));
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
