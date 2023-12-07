package vis.util;

import java.time.LocalDate;
import java.time.Period;

public class RecommendPackageUtil {

    /**
     * Recommends an insurance package based on the driver's age and the duration of the driving license.
     *
     * @param dateOfBirth    The date of birth of the driver.
     * @param licenseIssued  The date when the license was issued.
     * @param licenseExpires The date when the license expires.
     * @return Recommended insurance package.
     */
    public String recommendInsurancePackage(LocalDate dateOfBirth, LocalDate licenseIssued, LocalDate licenseExpires) {
        int age = calculateAge(dateOfBirth, LocalDate.now());
        int licenseDuration = calculateYearsBetween(licenseIssued, licenseExpires);

        // Example logic for recommendation
        if (age < 25) {
            return "Young Driver Package";
        } else if (licenseDuration <= 2) {
            return "New Driver Package";
        } else {
            return "Experienced Driver Package";
        }
    }

    /**
     * Calculates the age based on the current date and the date of birth.
     *
     * @param birthDate  The date of birth.
     * @param currentDate The current date.
     * @return Age in years.
     */
    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    /**
     * Calculates the number of years between two dates.
     *
     * @param start The start date.
     * @param end   The end date.
     * @return Number of years between the two dates.
     */
    private int calculateYearsBetween(LocalDate start, LocalDate end) {
        if ((start != null) && (end != null)) {
            return Period.between(start, end).getYears();
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        RecommendPackageUtil recommender = new RecommendPackageUtil();

        // Test the function with sample dates
        LocalDate dob = LocalDate.of(1995, 1, 15); // Date of Birth
        LocalDate licenseIssued = LocalDate.of(2015, 5, 20); // License Issue Date
        LocalDate licenseExpires = LocalDate.of(2025, 5, 20); // License Expiry Date

        String recommendedPackage = recommender.recommendInsurancePackage(dob, licenseIssued, licenseExpires);
        System.out.println("Recommended Insurance Package: " + recommendedPackage);
    }
}
