import java.util.List;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();

        City city = new City("Moscow");
        cityDAO.addCity(city);
        Employee employee = new Employee("John", "Doe", "Male", 28,1,city);
        employeeDAO.addEmployee(employee);
        employeeDAO.getAllEmployees().forEach(System.out::println);
        cityDAO.getAllCities().forEach(System.out::println);

        employee.setFirst_name("Johnathan");
        employee.setLast_name("Smith");
        employeeDAO.updateEmployee(employee);

        City updatedCity = cityDAO.getCityById(city.getId());
        updatedCity.setCity("Los Angeles");
        cityDAO.updateCity(updatedCity);

        System.out.println("Updated Employee: " + employeeDAO.getEmployeeById(employee.getId()));
        System.out.println("Updated City: " + cityDAO.getCityById(updatedCity.getId()));

        employeeDAO.deleteEmployee(employee);

        cityDAO.deleteCity(updatedCity);

        System.out.println("Employees after deletion:");
        employeeDAO.getAllEmployees().forEach(System.out::println);
        System.out.println("Cities after deletion:");
        cityDAO.getAllCities().forEach(System.out::println);

        employeeDAO.getAllEmployees().forEach(System.out::println);
        employeeDAO.addEmployee(employee);
        employeeDAO.deleteEmployee(employeeDAO.getEmployeeById(20));
        employeeDAO.updateEmployee(employee);
        System.out.println(employeeDAO.getEmployeeById(4));

        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("All employees: " + employees);



    }
}


