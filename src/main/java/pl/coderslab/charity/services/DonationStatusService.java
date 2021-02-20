package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.DonationStatusDto;
import pl.coderslab.charity.model.entities.DonationStatus;
import pl.coderslab.charity.model.repositories.DonationStatusRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.util.List;

@Service
@Transactional
public class DonationStatusService {

    private final DonationStatusRepository donationStatusRepository;
    private final ObjectMapper objectMapper;

    public DonationStatusService(DonationStatusRepository donationStatusRepository, ObjectMapper objectMapper) {
        this.donationStatusRepository = donationStatusRepository;
        this.objectMapper = objectMapper;
    }

    public void saveDonationStatus(DonationStatusDto donationStatusDto) {
        donationStatusRepository.save(objectMapper.convert(donationStatusDto, DonationStatus.class));
    }

    public void updateDonationStatus(DonationStatusDto donationStatusDto) {
        DonationStatus donationStatus = donationStatusRepository.findAllById(donationStatusDto.getId());
        donationStatus.setName(donationStatusDto.getName());
        donationStatus.setNameEng(donationStatusDto.getNameEng());
        donationStatusRepository.save(donationStatus);
    }

    public void deleteDonationStatus(Long id) {
        donationStatusRepository.deleteById(id);
    }

    public List<DonationStatusDto> getList() {
        return objectMapper.convertAll(donationStatusRepository.findAll(), DonationStatusDto.class);
    }

    public DonationStatusDto findById(Long id) {
        return objectMapper.convert(donationStatusRepository.findAllById(id), DonationStatusDto.class);
    }
}
