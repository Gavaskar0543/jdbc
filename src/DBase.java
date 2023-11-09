import java.sql.*;

import com.mysql.cj.jdbc.CallableStatement;

public class DBase {

	public static void main(String[] args) throws Exception {
		//System.out.println("hello alians");
		 // insertData();
		// insertUsingPst();
		//update();
		// readConnection();
		//deleteData();
	//	commit();
		batchProcessing();
	}

	private static void insertUsingPst()  throws Exception{
		
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		int id = 5;
		String name = "sandhya";
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
	private static void update() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		int id = 5;
		String Query = "update employee set salary = 60000 where id ="+id;
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		int row = st.executeUpdate(Query);
		System.out.println("rows added:" + "" + row);
		con.close();
	}
	private static void deleteData() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		int id = 1;
		String Query = "delete from employee where id = "+id;
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		int row = st.executeUpdate(Query);
		System.out.println("rows affected:" + "" + row);
		con.close();
	}
	private static void udate() throws Exception {
		String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		int id = 1;
		String Query = "delete from employee where id = "+id;
		Connection con = DriverManager.getConnection(url, userName, password);
		Statement st = con.createStatement();
		int row = st.executeUpdate(Query);
		System.out.println("rows affected:" + "" + row);
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
  public static void sp() throws Exception{
	  String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		Connection con = DriverManager.getConnection(url,userName,password);
		 java.sql.CallableStatement cst =  con.prepareCall("{call getEmployee()}");
		 ResultSet rs = cst.executeQuery();
		 while (rs.next()) {
				System.out.println("***From Db***");
				System.out.println("id:" + "" + rs.getInt(1));
				System.out.println("name:" + "" + rs.getString(2));
				System.out.println("salary:" + "" + rs.getInt(3));
			}
		 con.close();
  }
  
  public static void sp2() throws Exception{
	  String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		Connection con = DriverManager.getConnection(url,userName,password);
		 java.sql.CallableStatement cst =  con.prepareCall("{call getEmpById(?)}");
		 cst.setInt(1,2);
		 ResultSet rs = cst.executeQuery();
		 while (rs.next()) {
				System.out.println("***From Db***");
				System.out.println("id:" + "" + rs.getInt(1));
				System.out.println("name:" + "" + rs.getString(2));
				System.out.println("salary:" + "" + rs.getInt(3));
			}
		 con.close();
  }
  
  public static void spOut() throws Exception{
	  String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		Connection con = DriverManager.getConnection(url,userName,password);
		 java.sql.CallableStatement cst =  con.prepareCall("{call getNameById(?,?)}");
		 cst.setInt(1, 2);
		cst.registerOutParameter(2, Types.VARCHAR);
	   cst.executeUpdate();
	   System.out.print(cst.getString(2));
		 con.close();
  }
  
 //commit vs autocommit
  public static void commit() throws Exception{
	  String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		
		String query1  = "update employee set salary = 300000 where  id =2 ";
		String query2  = "update employee set salary = 300000 where  id =3";

		Connection con = DriverManager.getConnection(url,userName,password);
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		int row1  = st.executeUpdate(query1);
		System.out.println(row1+" "+"row affected");
		
		int row2  = st.executeUpdate(query2);
		System.out.println(row2+" "+"row affected");
		if(row1 >0 && row2 > 0)
			    con.commit();
		
		
		 con.close();
  }
  //batch processing
  
  public static void  batchProcessing() throws Exception{
	  String url = "jdbc:mysql://localhost:3306/jdbcDemo";
		String userName = "root";
		String password = "Gava050220#";
		
		String query1  = "update employee set salary = 50000 where  id =2 ";
		String query2  = "update employee set salary = 50000 where  id =3";
		String query3 = "update employee set salary = 50000  where id = 4";

		Connection con = DriverManager.getConnection(url,userName,password);
		//for rollback
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
	    int [] res = 	st.executeBatch();
	    
	    for(int i :res) {
	    	if(i > 0) {
	    		continue;
	    	}
	    	else {
	    		con.rollback();
	    	}
	    }
	    con.commit();

		
		
		 con.close();
  }
  
  
}
