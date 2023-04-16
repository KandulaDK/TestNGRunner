package functionalLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DBConnection {
	static Connection conn = null;
	public void createConnection(Properties prop, String dc) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String dbURL = "jdbc:oracle:thin:@" + prop.getProperty(dc + "_HOST") + ":" + prop.getProperty(dc + "_PORT") + "/" + prop.getProperty(dc + "_SERVICE");
			String userName = prop.getProperty(dc + "DBUSERNAME");
			String password = prop.getProperty(dc + "DBPASSWORD");
			conn = DriverManager.getConnection(dbURL, userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException sqlEx) {
			// TODO Auto-generated catch block
			sqlEx.printStackTrace();
		}
	}
	
	public List<HashMap<String, String>> executeQuery(Properties prop, String dc, String query, String[] fieldNames) throws SQLException {
		List<HashMap<String, String>> queryData = new ArrayList<HashMap<String, String>>();
		createConnection(prop, dc);
		Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery(query);
		queryData = convertResultSetToHashMap(resultSet, fieldNames);
		closeConnection();
		return queryData;
	}
	
	public void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed())
			conn.close();  
	}
	
	public List<HashMap<String, String>> convertResultSetToHashMap(ResultSet resultSet, String[] fieldNames) throws SQLException{
		List<HashMap<String, String>> queryData = new ArrayList<HashMap<String, String>>();
		
		while(resultSet.next()) {
			HashMap<String, String> queryRows = new HashMap<String, String>();
			for(int i = 0; i < fieldNames.length; i++) {
				queryRows.put(fieldNames[i], resultSet.getString(fieldNames[i]));
			}
			queryData.add(queryRows);
		}
		return queryData;
	}
}
