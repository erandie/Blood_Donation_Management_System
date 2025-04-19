package org.example.blood_donation_api.Service.Implementations;

import jakarta.transaction.Transactional;
import org.example.blood_donation_api.Entity.Donation;
import org.example.blood_donation_api.Entity.Employee;
import org.example.blood_donation_api.Repo.DonationRepo;
import org.example.blood_donation_api.Repo.EmployeeRepo;
import org.example.blood_donation_api.Service.DonationService;
import org.example.blood_donation_api.Util.ResponseUtil;
import org.example.blood_donation_api.dto.DonationDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<DonationDTO> getAllDonations() {
        List<Donation> donations = donationRepo.findAll();
        return modelMapper.map(donations, new TypeToken<List<DonationDTO>>() {
        }.getType());
    }

    @Override
    public void saveDonations(DonationDTO donationDTO) {
        if (donationRepo.existsById(donationDTO.getDonationId())) {
            throw new RuntimeException("Donation Already Exists!");
        }

        Donation donation = modelMapper.map(donationDTO, Donation.class);

        if (donationDTO.getEmpId() != null) {
            Employee employee = employeeRepo.findById(donationDTO.getEmpId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            donation.setEmployee(employee);
        }

        donationRepo.save(donation);
    }


    @Override
    public void updateDonations(DonationDTO donationDTO) {
        if (!donationRepo.existsById(donationDTO.getDonationId())) {
            throw new RuntimeException("Donation does not exist!");
        }
        donationRepo.save(modelMapper.map(donationDTO, Donation.class));
    }


    @Override
    public void deleteDonations(Integer donationId) {
        donationRepo.deleteById(donationId);
    }

    public long getDonationCount() {
        return donationRepo.count();
    }

    public DonationDTO searchById(Integer donationId) {
        Donation donation = donationRepo.findByDonationId(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        return modelMapper.map(donation, DonationDTO.class);
    }
}




























