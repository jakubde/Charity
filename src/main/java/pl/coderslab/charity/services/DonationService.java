package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.DonationRepository;
import pl.coderslab.charity.model.repositories.InstitutionRepository;
import pl.coderslab.charity.utils.ObjectMapper;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationService {

    private static final boolean DESC = false;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final ObjectMapper objectMapper;

    public DonationService(InstitutionRepository institutionRepository, DonationRepository donationRepository, ObjectMapper objectMapper) {
        this.institutionRepository = institutionRepository;
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

    public Double getDonationsPerDay() throws ParseException {

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Date currentDate = new Date(currentTimestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDonationDate = sdf.parse(donationRepository.firstDonationDate());

        long differenceInDays = ChronoUnit.DAYS.between(firstDonationDate.toInstant(),currentDate.toInstant());
        double donationsPerDay = 1.0 * donationSum()/differenceInDays;
        double donationsPerDayRounded = Math.round(donationsPerDay * 10) / 10.0;

        return donationsPerDayRounded;
    }

    public List<String> donationsSumsInLastTwelveMonths(){

        LocalDate date = LocalDate.now();
        List<String> sumsInLastTwelveMonthsList = new ArrayList<>();

        Integer totalDonations = donationSum();

        for (int i = 11; i >= 0; i--) {
            String currentDateWithoutIMonths = date.minusMonths(i).toString();
            Integer donationsToSubtract = donationRepository.sumDonationsWhereDateComesAfter(currentDateWithoutIMonths);
            if(donationsToSubtract == null){
                donationsToSubtract = 0;
            }

            sumsInLastTwelveMonthsList.add("" + (totalDonations - donationsToSubtract));
        }
        return sumsInLastTwelveMonthsList;
    }

    //Add an attribute(String) passed to the method that specifies the locale.
    public List<String> lastTwelveMonthsNames(){

        DateFormatSymbols dfs = new DateFormatSymbols(new Locale("pl"));
        String[] shortMonths = dfs.getShortMonths();

        List<String> shortMonthsList = new ArrayList<>(Arrays.asList(shortMonths));
        shortMonthsList.remove(shortMonthsList.size() - 1);

        //Capitalization of the first letters of months
        shortMonthsList = shortMonthsList.stream()
                .map(s -> {
            char firstChar = s.charAt(0);
            String firstLetter = ("" + firstChar).toUpperCase();
            String otherLetters = s.substring(1);
            return firstLetter + otherLetters;
        }).collect(Collectors.toList());

        String currentMonth = LocalDate.now().toString();
        int year = LocalDate.now().getYear();
        String yearlastTwoChars = ("" + year).substring(2);
        year = Integer.parseInt(yearlastTwoChars) - 1;
        char[] dateCharactersArray = currentMonth.toCharArray();
        currentMonth = "" + dateCharactersArray[5] + dateCharactersArray[6];
        int index = Integer.parseInt(currentMonth) - 1;
        List<String> chartLabels = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            index++;
            if(index > 11){
                index = 0;
                year += 1;
            }

            chartLabels.add(shortMonthsList.get(index) + " ’" + year);
        }
        return chartLabels;
    }

    public Map<Institution, Long> sumOfDonationsPerInstitution(){

        List<Institution> institutions = institutionRepository.findAll();
        List<Long> donationsPerInstitution = new ArrayList<>();

        for(Institution institution : institutions){

            Integer sumOfDonations;
            if(donationRepository.sumOfDonationsPerInstitution(institution.getId()) == null){
                sumOfDonations = 0;
            }
            else {
                sumOfDonations = donationRepository.sumOfDonationsPerInstitution(institution.getId());
            }

           donationsPerInstitution.add(Long.valueOf(sumOfDonations));
        }

        HashMap<Institution, Long> institutionsWithDonations = new HashMap<>();

        for (int i = 0; i < institutions.size(); i++) {
            institutionsWithDonations.put(institutions.get(i), donationsPerInstitution.get(i));
        }

        return sortByComparator(institutionsWithDonations, DESC);
    }

    //Add locale to give the name to the last element of list
    public List<String> pieChartLabels(){
        List<Institution> institutionList = new ArrayList<>(sumOfDonationsPerInstitution().keySet());

        List<String> labels = institutionList.stream().map(Institution::getName).map(s->s.replaceAll("\"", "'")).collect(Collectors.toList());

        if(labels.size() > 5){
            labels.subList(4, labels.size()).clear();
            labels.add("rest");
        }
        return labels;
    }

    public List<Long> pieChartValues(){
        List<Long> values = new ArrayList<>(sumOfDonationsPerInstitution().values());
        if(values.size() > 5){

            Long sumOfOtherInstitutionsValues = 0L;

            for (int i = 4; i < values.size(); i++){
                sumOfOtherInstitutionsValues += values.get(i);
            }
            values.subList(4, values.size()).clear();
            values.add(sumOfOtherInstitutionsValues);
        }
        return values;
    }



    private static Map<Institution, Long> sortByComparator(Map<Institution, Long> unsortMap, final boolean order)
    {
        List<Map.Entry<Institution, Long>> list = new LinkedList<>(unsortMap.entrySet());
        list.sort((o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        Map<Institution, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Institution, Long> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
