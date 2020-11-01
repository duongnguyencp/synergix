import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcConnectionDemo {
	public static void main(String args[]){
		try{
			//register driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish connection
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","duong123");
			
			//execute queries
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from department");
			
			while(rs.next()){
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
			con.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}