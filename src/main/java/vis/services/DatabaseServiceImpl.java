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
import vis.dto.request.VehicleRegistrationRequest;
import vis.entity.InsurancePackageEntity;
import vis.entity.UserEntity;
import vis.entity.VehicleEntity;
import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.SignupRequestSchema;
import vis.services.schema.VehicleRegistrationSchema;

import java.util.ArrayList;
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
        ArrayList<InsurancePackageSchema> insurancePackageSchemas = new ArrayList<>();

        UserEntity userEntity = getUser(userEmail);
        logger.info("Generating recommendation for: " + userEntity.getEmail());

        Session session = sessionFactory.openSession();
        String hql = "FROM InsurancePackageEntity";

        Query query = session.createQuery(hql);
        List<InsurancePackageEntity> entities = query.list();

        for (InsurancePackageEntity entity : entities) {
            insurancePackageSchemas.add(gson.fromJson(gson.toJson(entity), InsurancePackageSchema.class));
        }

        return insurancePackageSchemas;
    }

    @Override
    public boolean subscribe(String userEmail, String vehicleId, String packageId) {
        return false;
    }

    private UserEntity getUser(String email) {
        Session session = sessionFactory.openSession();
        String hql = "FROM UserEntity" + " WHERE email = " + "'" + email + "'";
        Query query = session.createQuery(hql);
        List<UserEntity> userEntities = query.list();

        if (!userEntities.isEmpty()) {
            return userEntities.get(0);
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

}
