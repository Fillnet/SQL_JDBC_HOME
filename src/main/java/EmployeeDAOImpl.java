import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private DataSource dataSource;

    public EmployeeDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addEmployee(Employee employee) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO employee (first_name, last_name, gender, age, city) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setString(1, employee.getFirst_name());
            stmt.setString(2, employee.getLast_name());
            stmt.setString(3, employee.getGender());
            stmt.setInt(4, employee.getAge());
            stmt.setString(5, employee.getCity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new Employee(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("city"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getInt("age"),
                        rs.getString("city"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE employee SET first_name = ?, last_name = ?, gender = ?, age = ?, city = ? WHERE id = ?")) {
            stmt.setString(1, employee.getFirst_name());
            stmt.setString(2, employee.getLast_name());
            stmt.setString(3, employee.getGender());
            stmt.setInt(4, employee.getAge());
            stmt.setString(5, employee.getCity());
            stmt.setInt(6, employee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEmployee(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
