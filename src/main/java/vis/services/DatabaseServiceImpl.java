package vis.services;

import com.google.gson.Gson;
import jade.core.Agent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vis.agents.AuthenticationAgent;
import vis.entity.InsurancePackageEntity;
import vis.entity.SubscriptionEntity;
import vis.entity.UserEntity;
import vis.entity.VehicleEntity;
import vis.services.schema.*;
import vis.util.RecommenderUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService {

	private Agent agent;

	private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	private SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

	private Gson gson = new Gson();

	@Override
	public boolean login(String email, String password) {
		Session session = sessionFactory.openSession();
		String hql = "FROM UserEntity" + " WHERE email = " + "'" + email + "'" + " AND password = " + "'" + password
				+ "'";

		Query query = session.createQuery(hql);
		List<UserEntity> userEntities = query.list();

		session.close();

		if (userEntities.isEmpty())
			return false;

		return true;
	}

	@Override
	public boolean signup(SignupRequestSchema userData) {
		UserEntity entity = gson.fromJson(gson.toJson(userData), UserEntity.class);

		logger.info("Writing entity: " + entity);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(entity);
		session.getTransaction().commit();
		session.close();

		return true;
	}

	@Override
	public ArrayList<InsurancePackageSchema> getPackages(String userEmail, String vehicleId) {
		VehicleEntity vehicle = getVehicle(vehicleId);
		logger.info("Generating recommendation for: " + userEmail);

		Session session = sessionFactory.openSession();
		String hql = "FROM InsurancePackageEntity";
		Query query = session.createQuery(hql);
		List entities = query.list();

		return RecommenderUtil.generateRecommendation(vehicle, entities);
	}

	public boolean subscribe(SubscriptionRequestSchema subscriptionRequestSchema) {
		SubscriptionEntity subscriptionEntity = new SubscriptionEntity(subscriptionRequestSchema.getUserEmail(),
				subscriptionRequestSchema.getVehicleId(), subscriptionRequestSchema.getPackageId(), new Date());
		logger.info("Writing entity: " + subscriptionEntity);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(subscriptionEntity);
		session.getTransaction().commit();
		session.close();

		return true;
	}

	private VehicleEntity getVehicle(String vehicleId) {
		Session session = sessionFactory.openSession();
		String hql = "FROM VehicleEntity" + " WHERE id = " + "'" + vehicleId + "'";
		Query query = session.createQuery(hql);
		List<VehicleEntity> vehicleEntities = query.list();

		if (!vehicleEntities.isEmpty()) {
			return vehicleEntities.get(0);
		}

		return null;
	}

	public boolean vehicleRegistration(VehicleRegistrationSchema vehicleRegistrationData) {
		VehicleEntity vehicleEntity = gson.fromJson(gson.toJson(vehicleRegistrationData), VehicleEntity.class);

		logger.info("Writing entity: " + vehicleEntity);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(vehicleEntity);
		session.getTransaction().commit();
		session.close();

		return true;
	}

	public UserProfileSchema getUserRequest(String email) {
		Session session = sessionFactory.openSession();
		String hql = "FROM UserEntity" + " WHERE email = " + "'" + email + "'";
		Query query = session.createQuery(hql);
		List<UserEntity> userEntities = query.list();

		if (userEntities.isEmpty()) {
			return null;
		}

		UserEntity user = userEntities.get(0);

		// Get registered vehicles
		hql = "FROM VehicleEntity" + " WHERE userEmail = " + "'" + user.getEmail() + "'";
		query = session.createQuery(hql);
		List<VehicleEntity> vehicleEntities = query.list();
		ArrayList<VehicleSchema> vehicles = new ArrayList<>();

		for (VehicleEntity vehicle : vehicleEntities) {
			vehicles.add(gson.fromJson(gson.toJson(vehicle), VehicleSchema.class));
		}

		// Get Subscriptions
		hql = "FROM SubscriptionEntity" + " WHERE userEmail = " + "'" + user.getEmail() + "'";
		query = session.createQuery(hql);
		List<SubscriptionEntity> subscriptionEntities = query.list();
		ArrayList<SubscribedPackageSchema> subscribedPackages = new ArrayList<>();

		for (SubscriptionEntity subscription : subscriptionEntities) {
			hql = "FROM InsurancePackageEntity" + " WHERE id = " + "'" + subscription.getPackageId() + "'";
			query = session.createQuery(hql);
			List packages = query.list();

			if (!packages.isEmpty()) {
				InsurancePackageEntity insurancePackage = (InsurancePackageEntity) packages.get(0);
				SubscribedPackageSchema packageSchema = gson.fromJson(gson.toJson(insurancePackage),
						SubscribedPackageSchema.class);
				packageSchema.setId(subscription.getId());
				packageSchema.setPackageId(insurancePackage.getId());
				packageSchema.setSubscribedOn(subscription.getSubscribedOn());
				packageSchema.setClaimedOn(subscription.getClaimedOn());
				subscribedPackages.add(packageSchema);
			}
		}

		return new UserProfileSchema(user.getId().toString(), user.getEmail(), user.getName(), user.getPhone(),
				user.getAddress(), user.getDob(), user.getHeight(), user.getGender(), user.getEyeColor(),
				user.getBloodGroup(), vehicles, subscribedPackages);
	}

	@Override
	public boolean claimInsurance(ClaimRequestSchema claimRequestSchema) {
		Session session = sessionFactory.openSession();
		SubscriptionEntity subscription = session.get(SubscriptionEntity.class, claimRequestSchema.getSubscriptionId());
		if (subscription != null) {
			subscription.setClaimedOn(new Date());
			session.beginTransaction();
			session.persist(subscription);
			session.getTransaction().commit();
			session.close();

			return true;
		}

		return false;
	}

}
