package connectDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMysql {
	
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "dragon";
    static final String PASS = "dragon";
    
    
    
    public static void main(String[] args) {
    	
    	 Connection conn = null;
         Statement stmt = null;
         
    /*     try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
         
         System.out.println("连接数据库...");
         try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         System.out.println(" 实例化Statement对...");
         try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String sql ="select * from person_tab";
         ResultSet rs = null;
         try {
			 rs = stmt.executeQuery(sql);
			 while (rs.next()) {
					int pid = rs.getInt("P_ID");
					String pname = rs.getNString("P_NAME");
					System.out.println("pid = "+pid  +"\t pname ="+pname);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
			rs.close();
			stmt.close();
	         conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        System.out.println("Done!"); 
         
	}
}
