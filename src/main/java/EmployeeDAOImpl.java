import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        entityManager.remove(entityManager.contains(employee) ? employee : entityManager.merge(employee));
    }
}
