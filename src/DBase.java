import java.sql.*;

public class DBase {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		String Query = "select  *  from employee";
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(Query);
		rs.next();
		System.out.println("***From Db***");
		System.out.println("id:" + "" + rs.getInt(1));
		System.out.println("name:" + "" + rs.getString(2));
		System.out.println("salary:" + "" + rs.getInt(3));

		con.close();
	}

}
