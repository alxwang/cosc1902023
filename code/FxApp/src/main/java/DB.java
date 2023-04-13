import java.sql.*;

public class DB {
    public static void showTable(Connection connection) throws SQLException {
        System.out.println("----------------------------------");
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM person");
        while(rs.next())
        {
            System.out.printf("Name=%s ID=%d\n",
                    rs.getString("name"),
                    rs.getInt("id"));
        }
    }
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS person");
            statement.executeUpdate("CREATE TABLE  person(id integer,name string)");
            statement.executeUpdate("INSERT INTO person VALUES(1,'Leo')");
            statement.executeUpdate("INSERT INTO person VALUES(2,'Alex')");
            showTable(connection);

            statement.executeUpdate("UPDATE person SET name='Joe' WHERE id=1");
            showTable(connection);

            statement.executeUpdate("DELETE from person WHERE id=2");
            showTable(connection);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES(?,?)");
            ps.setInt(1,1);//Param index start from 1
            ps.setString(2,"Max");
            ps.executeUpdate();

            showTable(connection);

            ps= connection.prepareStatement("SELECT * FROM person WHERE name=?");
            ps.setString(1,"Max");
            ResultSet rs = ps.executeQuery();
            if(rs.next())
//                System.out.println(rs.getInt("id"));
                    System.out.println(rs.getInt(1));//Col index start from 1


        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally {
            try
            {
                if(connection!=null)connection.close();
            }catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }


    }
}
