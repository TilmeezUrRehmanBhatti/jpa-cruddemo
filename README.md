## REST CRUD API with JPA

**Application Architecture**

<img src="https://user-images.githubusercontent.com/80107049/192894636-bc88f23c-09e5-4e8a-a105-f5eea74aabd3.png" width="500" />


**What is JPA?**

+ Java Presistence API(JPA)
    + Standard API for Object-to-Relation-Mapping (ORM)

+ Only a specification
    + Defines a set of interfaces
    + Requires an implementation to be usable

**JPA - Vendor Implementations**

<img src="https://user-images.githubusercontent.com/80107049/192894700-ee1efdf9-f5c2-4b55-b6a9-d50d7546d56d.png" wifth=500 />


**What are Benefits of JPA**

+ By having a standard API, we are not locked to vendor's implementation
+ Maintain portable, flexible code by coding to JPA spec(interfaces)
+ Can theoretically  switch vendor implementations
    + For example, if Vendor ABC stops supporting their product
    + We could switch to Vendor XYZ without vendor lock in



**Auto Data Source Configuration**

+ In Spring Boot, Hibernate is default implementation of JPA
+ **EntityManager** is similar to Hibernate **SessionFactory**
+ **EntityManager** can serve as a wrapper for Hibernate **Session** object
+ We can inject the **EntityManager** into our DAO

**Various DAO Techniques**

+ Version 1: Use EntityManager but leverage native Hibernate API
+ Version 2: Use EntityManager and standard JPA API
+ Version 3: Spring Data JPA

**Standard JSPA API**

+ The JPA API methods are similar to Native Hibernate API
+ JPA also support a query language: JPQL (JPQ Query Language)
    + For more details on JPQL https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage.htm#BNBTG

**Comparing JPA to Native Hibernate Methods**

| Action                    | native Hibernate method      | JPA method                     |
| ------------------------- | ---------------------------- | ------------------------------ |
| Create/save new entity    | session.save(...)            | entityManager.persist(...)     |
| Retrieve entity by id     | session.get(...) / load(...) | entitymanager.find(...)        |
| Retrieve list of entities | session.createQuery(...)     | entityManager.createQuery(...) |
| Save or update entity     | session.saveOrUpdate(...)    | entityManager.merge(...)       |
| Delete entity             | session.delete(...)          | entityManager.remove(...)      |

+ High-level comparison, Other options depending on context ...


**Development Process**

1. Set up Database Dev Environment
2. Create Spring Boot project using Spring Initializer
3. Get list of employees
4. Get single employee by ID
5. Add a new employee
6. Update an existing employee
7. Delete an existing employee

**DAO Interface**

```JAVA
public interface EmployeeDAO
  
  public List<Employee> findAll();
}
```

**DAO Implementation**

```JAVA
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
  
  private EntityManager entityManager;
  
  @Autowired
  public EmployeeDAOJpaImpl (EntityManager theEntityManager) {
    entityManager = theEntityManager;
  }
  
  @Override
  public List<Employee> findAll() {
  
    // create a query
    TypedQuery<Employee> theQuery = 
      entityManager.createQuery("from Employee", Employee.class);
    
    // execute query and get result list
    List<Employee> employees = theQuery.getResultList();
    
    // return the result
    return employees;
}
```


**Add or Update employee**

```JAVA
@Override
public void save(Employee theEmployee) {
  
  // save or update the employee
  Employee dbEmployee = entityManager.merge(theEmployee);
  
  // Update with id from db ... so we can get generated id for save/insert
  theEmployee.setId(dbEmployee.getId());
}
```

+ `entityManager.merge(theEmployee)` if id == 0 then save/insert else update
+ `theEmployee.setId(dbEmployee.getId());` useful in our REST API to return generated id 


