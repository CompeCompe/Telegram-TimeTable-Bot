
import java.sql.*;

public class WorkWithDB {
    static String connectionDB(int i) throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/timetable", "root", "zxcvasdf");
        final String query = "select*from table1 where id = "+i+" ";
        final Statement stmt = connection.createStatement();
        final ResultSet rs = stmt.executeQuery(query);

        if (rs.next()) {
            String lesson1 = rs.getString(4);
            String lesson2 = rs.getString(5);
            String lesson3 = rs.getString(6);
            String result = "Расписание на этот день: " + lesson1 + "\n" + lesson2 + "\n" + lesson3;
            return result;
        }
        connection.close();

        return "";
    }

}
