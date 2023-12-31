# Vehicle Insurance System (VIS)
VIS is an agent-based software system that demonstrates how modern frameworks like Spring Boot, React can be integrated with agent-based software engineering. 
To our knowledge, this project represents the initial effort in successfully integrating Spring Boot with JADE, creating a system that bridges the two technologies.
We adopt a multilayered architecture that separates the controllers, agents, and the services.

### Environmental Requirements
- JDK 11 +
- JADE 4+
- Postgres 16
- Maven
- node 14 +

### Backend Setup
We recommend using IntellijIDEA to run this project. Here is a step-by-step guide to run this project.
1. Clone this repository.
    ```shell
    # SSH
    git clone git@github.com:afifaniks/vis-jade.git
    # HTTPS
    git clone https://github.com/afifaniks/vis-jade.git 
    ```
2. Open the project from IntellijIDEA.
3. Select `pom.xml` file and sync with Maven.
4. Open `pgAdmin 4` and create a new database called `vis`.
5. The default username, password, and port for the database is `postgres`, `1234`, and `5432`.
6. If you need to change that, you can change all the details from `src/main/resources/hibernate.cfg.xml` file.
7. Write sample data to the database by running `src/test/java/vis/WriteSampleData.java` this file.

You are now ready to run the system components.
### Run Agents
Assuming you are using IntellijIDEA, you can configure the agents with the following:
- Create a new `Run Configuration`.
- Provide a name to the configuration as you want.
- Select ```jade.Boot``` as Main Class.
- Pass the following program arguments.
    ```
    -agents Admin:vis.agents.AdminAgent;Authentication:vis.agents.AuthenticationAgent;CustomerAssistant:vis.agents.CustomerAssistantAgent;InsuranceClaim:vis.agents.InsuranceClaimAgent;Database:vis.agents.DatabaseAgent;Verification:vis.agents.VerificationAgent
    ```
All the necessary tables should be automatically created in this step.
### Run Backend Controller
Run ```VisJadeApplication``` from ```src/main/java/VisJadeApplicatiom```

If everything goes well, the Spring Boot application should be running. As we have integrated `Swagger UI`, you should be able to see the
API documentation in this link: http://localhost:8080/swagger-ui/index.html#/

### Frontend Setup
- Clone frontend repository
    ```shell
    # SSH
    git clone git@github.com:salsina/vehicle-insurance-system--frontend.git
   # HTTPS
    git clone https://github.com/salsina/vehicle-insurance-system--frontend.git
    ```
- Change directory
  `cd vehicle-insurance-system--frontend`
- Install dependency
    ```shell
    npm install --force
    ```
- Run the React application
  ```
  npm start
  ```
- You should now be able to interact with the application on http://localhost:3000

### Project Structure
```
vis
|- agents: Contains agents and behaviors of the agents
|- constants: Constant values used throughout the package
|- controllers: API controller
|- dto: Data transfer objects for the inbound and outbound requests in the backend
|- entity: Objects meant to be persisted in the database
|- ontology: Defined ontology, concepts, predicates, and actions used in the system
|- security: API security components
|- services: Service interfaces, their implementations, and the schemas used by the services
|- util: Utility classes
```
