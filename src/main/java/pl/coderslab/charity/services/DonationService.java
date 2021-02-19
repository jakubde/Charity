package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.model.dtos.DonationDto;
import pl.coderslab.charity.model.entities.Category;
import pl.coderslab.charity.model.entities.Donation;
import pl.coderslab.charity.model.entities.DonationStatus;
import pl.coderslab.charity.model.entities.Institution;
import pl.coderslab.charity.model.repositories.*;
import pl.coderslab.charity.utils.ObjectMapper;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DonationService {

    private static final boolean DESC = false;
    private final DonationStatusRepository donationStatusRepository;
    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public DonationService(DonationStatusRepository donationStatusRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository, CategoryRepository categoryRepository, UserRepository userRepository, ObjectMapper objectMapper) {
        this.donationStatusRepository = donationStatusRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    public void saveDonation(DonationDto donationDto) {
        donationDto.setDonationStatusId(1L);
        Donation donation = objectMapper.convert(donationDto, Donation.class);
        List<Category> categoryList = new ArrayList<>();
        for (Long categoryId : donationDto.getCategoryIdList()) {
            categoryList.add(categoryRepository.findAllById(categoryId));
        }
        donation.setCategories(categoryList);
        donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteRelatedDataFromDonationCategoryTable(id);
        donationRepository.deleteById(id);
    }

    public List<DonationDto> getList() {
        List<DonationDto> donationDtoList = objectMapper.convertAll(donationRepository.findAll(), DonationDto.class);

        for (DonationDto donationDto : donationDtoList) {
            donationDto.setCategoryIdList(donationRepository.findAllCategoryIdsByDonationId(donationDto.getId()));
        }

        return donationDtoList;
    }

    public Integer donationSum() {

        if (donationRepository.sumAllDonations() == null) {
            return 0;
        } else {
            return donationRepository.sumAllDonations();
        }
    }

    public int distinctInstitutionsCount() {
        return donationRepository.countAllDistinctInstitutions();
    }

    public Double getDonationsPerDay() throws ParseException {

        if (donationRepository.firstDonationDate() == null) {
            return null;
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Date currentDate = new Date(currentTimestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date firstDonationDate = sdf.parse(donationRepository.firstDonationDate());

        long differenceInDays = ChronoUnit.DAYS.between(firstDonationDate.toInstant(), currentDate.toInstant());
        double donationsPerDay = 1.0 * donationSum() / differenceInDays;
        double donationsPerDayRounded = Math.round(donationsPerDay * 10) / 10.0;

        return donationsPerDayRounded;
    }

    public List<String> donationsSumsInLastTwelveMonths() {
        LocalDate currentDate = LocalDate.now();
        List<String> sumsInLastTwelveMonthsList = new ArrayList<>();

        Integer totalDonations = donationSum();

        for (int i = 0; i < 12; i++) {
            String lastDayOfSpecificMonth;
            if (currentDate.getMonth().getValue() - i < 1) {
                if ((currentDate.getYear() - 1) % 4 == 0) {
                    lastDayOfSpecificMonth = currentDate
                            .withMonth(12 - Math.abs(currentDate.getMonth().getValue() - i))
                            .withDayOfMonth(currentDate.withMonth(12 - Math.abs(currentDate.getMonth().getValue() - i)).getMonth().maxLength())
                            .withYear(currentDate.getYear() - 1)
                            .toString();
                } else {
                    lastDayOfSpecificMonth = currentDate
                            .withMonth(12 - Math.abs(currentDate.getMonth().getValue() - i))
                            .withDayOfMonth(currentDate.withMonth(12 - Math.abs(currentDate.getMonth().getValue() - i)).getMonth().maxLength() - (currentDate.withMonth(currentDate.getMonth().getValue() - i).getMonth().getValue() == 2 ? 1 : 0))
                            .withYear(currentDate.getYear() - 1)
                            .toString();
                }
            } else {
                if (currentDate.getYear() % 4 == 0) {
                    lastDayOfSpecificMonth = currentDate
                            .withMonth(currentDate.getMonth().getValue() - i)
                            .withDayOfMonth(currentDate.withMonth(currentDate.getMonth().getValue() - i).getMonth().maxLength())
                            .toString();
                } else {
                    lastDayOfSpecificMonth = currentDate
                            .withMonth(currentDate.getMonth().getValue() - i)
                            .withDayOfMonth(currentDate.withMonth(currentDate.getMonth().getValue() - i).getMonth().maxLength() - (currentDate.withMonth(currentDate.getMonth().getValue() - i).getMonth().getValue() == 2 ? 1 : 0))
                            .toString();

                }
            }
            Integer bagsNumber = donationRepository.sumDonationsWhereDateComesBefore(lastDayOfSpecificMonth);
            if (bagsNumber == null) {
                bagsNumber = 0;
            }

            sumsInLastTwelveMonthsList.add("" + bagsNumber);
        }
        Collections.reverse(sumsInLastTwelveMonthsList);
        return sumsInLastTwelveMonthsList;
    }

    public List<String> donationsInLastTwelveMonths() {
        List<String> donationsInTheLastTwelveMonthsList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonth().getValue();

        for (int i = 0; i < 12; i++) {

            int monthToCheck = currentMonth - i;
            String monthToCheckString;
            int yearToCheck;

            if (monthToCheck > 0) {
                yearToCheck = currentYear;
            } else {
                yearToCheck = currentYear - 1;
                monthToCheck += 12;
            }

            if (monthToCheck < 10) {
                monthToCheckString = "0" + monthToCheck;
            } else {
                monthToCheckString = "" + monthToCheck;
            }

            String dateToQueryInRepository = yearToCheck + "-" + monthToCheckString;

            String donationsInSpecificMonth = donationRepository.getDonationsSumInGivenMonth(dateToQueryInRepository);
            if (donationsInSpecificMonth == null) {
                donationsInSpecificMonth = "0";
            }

            donationsInTheLastTwelveMonthsList.add(donationsInSpecificMonth);
        }
        Collections.reverse(donationsInTheLastTwelveMonthsList);
        return donationsInTheLastTwelveMonthsList;
    }

    public List<String> lastTwelveMonthsNames(Locale locale) {

        DateFormatSymbols dfs = new DateFormatSymbols(locale);
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
        String yearLastTwoChars = ("" + year).substring(2);
        year = Integer.parseInt(yearLastTwoChars) - 1;
        char[] dateCharactersArray = currentMonth.toCharArray();
        currentMonth = "" + dateCharactersArray[5] + dateCharactersArray[6];
        int index = Integer.parseInt(currentMonth) - 1;
        List<String> chartLabels = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            index++;
            if (index > 11) {
                index = 0;
                year += 1;
            }

            chartLabels.add(shortMonthsList.get(index) + " ’" + year);
        }
        return chartLabels;
    }

    public Map<Institution, Long> sumOfDonationsPerInstitution() {

        List<Institution> institutions = institutionRepository.findAll();
        List<Long> donationsPerInstitution = new ArrayList<>();

        for (Institution institution : institutions) {

            Integer sumOfDonations;
            if (donationRepository.sumOfDonationsPerInstitution(institution.getId()) == null) {
                sumOfDonations = 0;
            } else {
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

    public List<String> chartLabels(String otherInstitutionsLabelInCorrectLang) {
        List<Institution> institutionList = new ArrayList<>(sumOfDonationsPerInstitution().keySet());

        List<String> labels = institutionList.stream().map(Institution::getName).map(s -> s.replaceAll("\"", "'")).collect(Collectors.toList());

        if (labels.size() > 5) {
            labels.subList(4, labels.size()).clear();
            labels.add(otherInstitutionsLabelInCorrectLang);
        }
        return labels;
    }

    public List<Long> pieChartValues() {
        List<Long> values = new ArrayList<>(sumOfDonationsPerInstitution().values());
        if (values.size() > 5) {

            Long sumOfOtherInstitutionsValues = 0L;

            for (int i = 4; i < values.size(); i++) {
                sumOfOtherInstitutionsValues += values.get(i);
            }
            values.subList(4, values.size()).clear();
            values.add(sumOfOtherInstitutionsValues);
        }
        return values;
    }

    private static Map<Institution, Long> sortByComparator(Map<Institution, Long> unsortedMap, final boolean order) {
        List<Map.Entry<Institution, Long>> list = new LinkedList<>(unsortedMap.entrySet());
        list.sort((o1, o2) -> {
            if (order) {
                return o1.getValue().compareTo(o2.getValue());
            } else {
                return o2.getValue().compareTo(o1.getValue());

            }
        });

        Map<Institution, Long> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Institution, Long> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email).getId();
    }

    public Map<Long, String> getCategoryMap() {
        Map<Long, String> categoryMap = new HashMap<>();
        for (Category category : categoryRepository.findAll()) {
            categoryMap.put(category.getId(), category.getName());
        }
        return categoryMap;
    }

    public Map<Long, List<String>> getInstitutionMap() {
        Map<Long, List<String>> institutionMap = new HashMap<>();
        for (Institution institution : institutionRepository.findAll()) {
            List<String> namesInDifferentLanguagesList = new ArrayList<>();
            namesInDifferentLanguagesList.add(institution.getName().replace("\"", "&quot;"));
            namesInDifferentLanguagesList.add(institution.getNameEng().replace("\"", "&quot;"));
            //replacing double quote symbol with its HTML code to properly display institution names in views
            institutionMap.put(institution.getId(), namesInDifferentLanguagesList);
        }
        return institutionMap;
    }

    public Map<Long, String> getDonationStatusMap() {
        Map<Long, String> donationStatusMap = new HashMap<>();
        for (DonationStatus donationStatus : donationStatusRepository.findAll()) {
            donationStatusMap.put(donationStatus.getId(), donationStatus.getName());
        }
        return donationStatusMap;
    }

    public void createDonation(String userEmail, String categoryIdListAsString, Long institutionId, Long statusId, DonationDto donationDto) {
        donationDto.setInstitutionId(institutionId);
        donationDto.setDonationStatusId(statusId);
        Donation donation = objectMapper.convert(donationDto, Donation.class);
        donation.setUser(userRepository.findByEmail(userEmail));

        List<Category> categoryList = Arrays.stream(categoryIdListAsString.split(",")).map(x -> categoryRepository.findAllById(Long.parseLong(x))).collect(Collectors.toList());
        donation.setCategories(categoryList);

        donationRepository.save(donation);
    }

    public DonationDto findDonationById(Long id) {
        DonationDto donationDto = objectMapper.convert(donationRepository.findAllById(id), DonationDto.class);
        donationDto.setCategoryIdList(donationRepository.findAllCategoryIdsByDonationId(donationDto.getId()));
        return donationDto;
    }

    public Long getInstitutionIdByName(String name) {
        return institutionRepository.getIdByName(name);
    }

    public void changeDonationStatus(Long donationId, Long donationStatusId) {
        Donation donation = donationRepository.findAllById(donationId);
        DonationStatus donationStatus = donationStatusRepository.findAllById(donationStatusId);
        donation.setDonationStatus(donationStatus);
        donationRepository.save(donation);
    }

    public LocalTime timeInProperFormat(String pickUpTime) {

        pickUpTime = pickUpTime.replaceAll("\\s+","");  //removes all whitespaces
        
        if (pickUpTime.contains("AM") || pickUpTime.contains("PM")) {
            
            String pickUpTimePrefix = pickUpTime.substring(0, 5);   //e.g. 11:31
            String pickUpTimeSuffix = pickUpTime.substring(5, pickUpTime.length()); //e.g. AM
            pickUpTime = pickUpTimePrefix + " " + pickUpTimeSuffix;

            DateTimeFormatter twelveHourFormatter = DateTimeFormatter.ofPattern("hh:mm a");

            LocalTime localTime = LocalTime.parse(pickUpTime, twelveHourFormatter);
            return localTime;
            
        } else {
            
            DateTimeFormatter twentyFourHourFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(pickUpTime, twentyFourHourFormatter);
            return localTime;
        }
    }
}
