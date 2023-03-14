import java.sql.*;

public class Application {

    public static void main(String[] args) {


        final String user = "postgres";
        final String password = "6565";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // id работника, данные о котором мы хотим получить
            int employeeId = 1;
            String sql = "SELECT first_name, last_name, gender, city_id FROM employee WHERE id = ?";
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
