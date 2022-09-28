## REST CRUD API with Hibernate

REST API with Spring Boot that connects to a database

<img src="https://user-images.githubusercontent.com/80107049/192886903-bfe24fa0-5194-4325-9871-34cab4397884.png" width="500" />


**API Requirements**

Create a REST API for the Employee Directory

REST clients should be able to
+ Get a list of employees
+ Get a single employee by id
+ Add a new employee
+ Update an employee
+ Delete an employee


**REST API**

| HTTP Method | Endpoint                    | CRUD Action                 |
| ----------- | --------------------------- | --------------------------- |
| POST        | /api/employees              | Create a new employee       |
| GET         | /api/employees              | Read a list of employees    |
| GET         | /api/employees/{employeeId} | Read a single employee      |
| PUT         | /api/employees              | Update an existing employee |
| DELETE      | /api/employees/{employeeId} | Delete an existing employee |


**Development Process**

1. Set up Database Dev Environment
2. Create Spring Boot project using Spring Initializer
3. Get list of employees
4. Get single employee by ID
5. Add a new employee
6. Update an existing employee
7. Delete an existing employee

**Application Architecture**

<img src="https://user-images.githubusercontent.com/80107049/192887021-f4b6c120-4707-453f-92ff-49a5c0b2d361.png" width="500" />



**Integrating Hibernate and JPA**

_Development Process_

1. Update db configuration in application.properties
2. Create Employee entity
3. Create DAO interface
4. Create DAO implementation
5. Create REST controller to use DAO


**DAO Interface**

```JAVA
public interface EmployeeDAO
  
  public List<Employee> findAll();
}
```

**DAO Implementation**

```JAVA
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
  
  private EntityManager entityManager;
  
  @Autowired
  public EmployeeDAOHibernateImpl (EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }
  
  @Override
  @Transactional
  public List<Employee> findAll() {
    
    // get the current hibernate session
    Session currentSession = entityManager.unwrap(Session.class);
    
    // create a query
    Query<Employee> theQuery = 
      currentSession.createQuery("from Employee", Employee.class);
    
    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();
    
    // return the result
    return employees;
}
```
+ `EntityManager theEntityManager` Automatically created by Spring Boot
+ We use here Constructor Injection
+ `@Autowired`is optional because in spring latest version when class has one constructor then no need to use @Autowired  