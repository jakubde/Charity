package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;

@Service
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final ObjectMapper objectMapper;

    public DonationService(DonationRepository donationRepository, ObjectMapper objectMapper) {
        this.donationRepository = donationRepository;
        this.objectMapper = objectMapper;
    }

    public void saveDonation(DonationDto donationDto){
        donationRepository.save(objectMapper.convert(donationDto, Donation.class));
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    public List<DonationDto> getList(){
        return objectMapper.convertAll(donationRepository.findAll(), DonationDto.class);
    }

    public Integer donationSum(){
        return donationRepository.sumAllDonations();
    }

    public int distinctInstitutionsCount(){
        return donationRepository.countAllDistinctInstitutions();
    }

}
