import java.sql.*;

public class DBase {

	public static void main(String[] args) throws Exception {
		
		// insertData();
		insertUsingPst();
		readConnection();
	}

	private static void insertUsingPst()  throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		int id = 3;
		String name = "varun";
		int salary = 52000;
		String Query = "insert into employee values(?,?,?)";
		Connection con = DriverManager.getConnection(url, userName, password);
		PreparedStatement pst = 	con.prepareStatement(Query);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, salary);
		int row = pst.executeUpdate();
		System.out.println("rows added:" + "" + row);
		con.close();
	}

	private static void insertData() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		String Query = "insert into employee values(2,'ajay',620000)";
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		int row = st.executeUpdate(Query);
		System.out.println("rows added:" + "" + row);
		con.close();
	}

	private static void readConnection() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		String Query = "select  *  from employee";
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(Query);
		while (rs.next()) {
			System.out.println("***From Db***");
			System.out.println("id:" + "" + rs.getInt(1));
			System.out.println("name:" + "" + rs.getString(2));
			System.out.println("salary:" + "" + rs.getInt(3));
		}

		con.close();

	}

}
