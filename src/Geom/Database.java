package Geom;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * this class represent the connect with the data base
 * she knew to calculate our score and the average score for every game
 */
public class Database {
	public  double main(int idmap)
	{
		double averega=0;
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);


			Statement statement = connection.createStatement();

			//select data
			String allCustomersQuery = "SELECT AVG(Point) FROM logs where SomeDouble=" + idmap;
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			resultSet.next();
		
			averega =resultSet.getDouble(1);

				resultSet.close();		
				statement.close();		
				connection.close();	
				
			}

			catch (SQLException sqle) {
				System.out.println("SQLException: " + sqle.getMessage());
				System.out.println("Vendor Error: " + sqle.getErrorCode());
			}

			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return averega;
		}
	

	}