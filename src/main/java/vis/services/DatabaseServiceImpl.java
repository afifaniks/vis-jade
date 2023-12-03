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
import vis.constants.DBTableNames;
import vis.entity.UserEntity;
import vis.services.schema.InsurancePackageSchema;
import vis.services.schema.SignupRequestSchema;

import java.util.ArrayList;
import java.util.List;

public class DatabaseServiceImpl implements DatabaseService{

    private Agent agent;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationAgent.class);

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

    private SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    private Gson gson = new Gson();


    @Override
    public boolean login(String email, String password) {
        Session session = sessionFactory.openSession();
		String hql = "FROM UserEntity"
                + " WHERE email = "
                + "'" + email + "'"
                + " AND password = "
                + "'" + password + "'" ;

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
        return null;
    }

    @Override
    public boolean subscribe(String userEmail, String vehicleId, String packageId) {
        return false;
    }
}
