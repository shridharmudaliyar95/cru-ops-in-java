import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReadStatement {
    private static final String QUERY = "SELECT `user_id`, `user_mail`, `user_pwd`, `role` FROM `users`";

    public static void main(String[] args) {

        // using try-with-resources to avoid closing resources (boiler plate code)

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            System.out.println(preparedStatement+"\n");
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("role");
                String email = rs.getString("user_mail");
                String password = rs.getString("user_pwd");
                System.out.println(id + "," + name + "," + email + "," + " and " + "," + password);
            }
        } catch (SQLException e) {
        	JDBCUtils.printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
    }
}