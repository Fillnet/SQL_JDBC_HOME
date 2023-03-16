import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

import org.springframework.context.annotation.ComponentScan;
@ComponentScan
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(HibernateConfig.class);
        context.refresh();

        EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);

        Employee employee1 = new Employee();
        employee1.setFirst_name("John");
        employee1.setLast_name("Doe");
        employee1.setGender("Male");
        employee1.setAge(30);
        employee1.setCity(1);

        employeeDAO.addEmployee(employee1);

        Employee employee2 = new Employee();
        employee2.setFirst_name("Jane");
        employee2.setLast_name("Doe");
        employee2.setGender("Female");
        employee2.setAge(28);
        employee2.setCity(2);

        employeeDAO.addEmployee(employee2);

        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("All employees: " + employees);

        Employee employeeToUpdate = employeeDAO.getEmployeeById(1);
        employeeToUpdate.setAge(35);
        employeeDAO.updateEmployee(employeeToUpdate);

        Employee employeeToDelete = employeeDAO.getEmployeeById(2);
        employeeDAO.deleteEmployee(employeeToDelete);

        employees = employeeDAO.getAllEmployees();
        System.out.println("All employees after update and delete: " + employees);

        context.close();
    }
}


