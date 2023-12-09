package vis.util;

import com.google.gson.Gson;
import vis.entity.InsurancePackageEntity;
import vis.entity.VehicleEntity;
import vis.services.schema.InsurancePackageSchema;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * This class is used to generate recommendations based on the vehicle's mileage.'
 */
public class RecommenderUtil {

	/***
	 * This method is used to generate recommendations based on the vehicle's mileage.'
	 * @param vehicleEntity The vehicle entity.
	 * @param birthdate The DOB of the user.
	 * @param entities The list of entities.
	 * @return A list of recommendations.
	 */
	public static ArrayList<InsurancePackageSchema> generateRecommendation(String birthdate,
			VehicleEntity vehicleEntity, List<InsurancePackageEntity> entities) {
		ArrayList<InsurancePackageSchema> recommendations = new ArrayList<>();

		if (vehicleEntity == null) {
			return recommendations;
		}

		int userCurrentAge = calculateAge(birthdate);

		List<String> packageForNewCars = Arrays.asList("Premium", "Classic");
		List<String> packageForOldCars = Arrays.asList("Starter", "Classic");
		List<String> packageForAverage = Arrays.asList("Basic", "Classic");
		List<String> packageForYoung = Arrays.asList("Young");
		List<String> packageForSenior = Arrays.asList("Senior");

		for (InsurancePackageEntity insurancePackage : entities) {
			if (vehicleEntity.getMileage() <= 10000) {
				if (packageForNewCars.contains(insurancePackage.getPackageName())) {
					addPackageToList(insurancePackage, recommendations);
				}
			}
			if (vehicleEntity.getMileage() > 10000 && vehicleEntity.getMileage() <= 100000) {
				if (packageForAverage.contains(insurancePackage.getPackageName())) {
					addPackageToList(insurancePackage, recommendations);
				}
			}
			if (vehicleEntity.getMileage() > 100000) {
				if (packageForOldCars.contains(insurancePackage.getPackageName())) {
					addPackageToList(insurancePackage, recommendations);
				}
			}

			if (userCurrentAge >= 18 && userCurrentAge < 30) {
				if (packageForYoung.contains(insurancePackage.getPackageName())) {
					addPackageToList(insurancePackage, recommendations);
				}
			}

			if (userCurrentAge >= 30) {
				if (packageForSenior.contains(insurancePackage.getPackageName())) {
					addPackageToList(insurancePackage, recommendations);
				}
			}
		}

		return recommendations;

	}

	/***
	 * This method is used to add the insurance package to the list of recommendations.
	 * @param insurancePackage The insurance package to be
	 * @param recommendations The list of recommendations to be
	 */
	private static void addPackageToList(InsurancePackageEntity insurancePackage,
			ArrayList<InsurancePackageSchema> recommendations) {
		Gson gson = new Gson();

		InsurancePackageSchema packageSchema = gson.fromJson(gson.toJson(insurancePackage),
				InsurancePackageSchema.class);
		packageSchema.setPackageId(insurancePackage.getId());
		recommendations.add(packageSchema);
	}

	/***
	 * This method is used to calculate the age of the user.
	 * @param birthdate The date of birth of the user.
	 * @return The age of the user.
	 */
	private static int calculateAge(String birthdate) {
		LocalDate birthDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate currentDate = LocalDate.now();
		return Period.between(birthDate, currentDate).getYears();
	}

}
