import java.util.List;

public class Application {
    public static void main(String[] args) {
        Employee employee = new Employee("John", "Doe", "Male", 35, 2);
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        employeeDAO.getAllEmployees().forEach(System.out::println);
        employeeDAO.addEmployee(employee);
        employeeDAO.deleteEmployee(employeeDAO.getEmployeeById(13));
        employeeDAO.updateEmployee(employee);
        System.out.println(employeeDAO.getEmployeeById(4));

        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("All employees: " + employees);

    }
}


