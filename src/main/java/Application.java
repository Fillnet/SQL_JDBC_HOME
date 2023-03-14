import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.List;


public class Application {

    public static void main(String[] args) throws SQLException {
        // Создаем объект DataSource для PostgreSQL
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("skypro");
        dataSource.setUser("postgres");
        dataSource.setPassword("6565");

        // создание объекта EmployeeDAO для работы с таблицей сотрудников
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(dataSource);

        // создание нового сотрудника и добавление его в базу данных
        Employee newEmployee = new Employee(7, "John", "Doe", "male", 30,"New York");
        employeeDAO.addEmployee(newEmployee);
        Employee employee = new Employee(1, "John", "Doe", "Male", 35, "New York");
        employeeDAO.updateEmployee(employee);
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        final String user = "postgres";
        final String password = "6565";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // id работника, данные о котором мы хотим получить
            int employeeId = 1;
            String sql = "SELECT first_name, last_name, gender, city FROM employee WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String city = resultSet.getString("city");
                System.out.println("First name: " + firstName);
                System.out.println("Last name: " + lastName);
                System.out.println("Gender: " + gender);
                System.out.println("City: " + city);
            } else {
                System.out.println("No employee found with id " + employeeId);
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());

        }

    }


}
