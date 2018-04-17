package connectDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectOracle {
	private static Connection con = null; // 数据库连接对象  
    private static PreparedStatement psmt = null; // 预编译语句对象  
    private static ResultSet result = null;// 结果集对象  
    private static List<Integer> dismeunIdList = new ArrayList<Integer>();
    /** 
     * 建立数据库连接 
     *  
     * @return 
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */  
    public static Connection setUpConnection() throws ClassNotFoundException, SQLException {  
        Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载Oracle驱动程序  
        String url = "jdbc:oracle:thin:@" + "localhost:orcl"; // 连接使用的url  
        String user = "ROOT"; // 数据库用户名  
        String password = "ROOT"; // 密码  
        con = DriverManager.getConnection(url, user, password);// 获取连接  
        return con;  
    }  
    
    
    /** 
     * 执行查询 
     *  SELECT DISTINCT USER_ID FROM "ROOT"."USER_MENU";
     *  SELECT USER_ID FROM "ROOT"."USER_TAB" WHERE USER_LEV_ID IN (2,3,4);
     * @return 
     * @throws SQLException 
     */  
    public static ResultSet query() throws SQLException {  
        StringBuilder sql = new StringBuilder("SELECT USER_ID ").  
                append("FROM USER_TAB WHERE USER_LEV_ID IN (2,3,4)");
                //.  
                //append(" WHERE USER_ENTRY=?");  
        psmt = con.prepareStatement(sql.toString()); // 预编译SQL  
       // psmt.setString(1, "qtglcs"); // 设置参数  
        result = psmt.executeQuery(); // 执行SQL  
        return result;  
    }  
    
    
    /** 
     * 分析查询结果集的元数据信息 
     *  
     * @throws SQLException 
     */  
  /*  public static void analyzeResultSet() throws SQLException {  
        ResultSetMetaData rsmd = null;  
        StringBuilder sb = null;  
        rsmd = result.getMetaData();// 获取查询游标的元数据对象  
        sb = new StringBuilder();  
        System.out.println("列数目：" + rsmd.getColumnCount());  
        for (int i = 0; i < rsmd.getColumnCount(); i++) {  
            sb.append("列名：" + rsmd.getColumnName(i + 1) + " ");  
            sb.append(rsmd.getColumnType(i + 1) + " ");  
            sb.append(rsmd.getColumnLabel(i + 1) + " ");  
            sb.append("列最大宽度：" + rsmd.getColumnDisplaySize(i + 1) + "个字符 ");  
            sb.append("数据类型：" + rsmd.getColumnTypeName(i + 1));  
            //System.out.println(sb.toString());  
            sb.delete(0, sb.length());  
        }  
    }  
    */
    
    /** 
     * 打印查询到的结果 
     *  
     * @throws SQLException 
     */  
    public static void printResult() throws SQLException {  
        StringBuilder sb = null;  
        sb = new StringBuilder();  
        while (result.next()) {  
            for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {  
            	dismeunIdList.add(Integer.parseInt(result.getString(i + 1)));
                sb.append(result.getString(i + 1) + " ");  
            }  
            System.out.println(sb.toString());  
            sb.delete(0, sb.length());  
        }  
    }  
    
    
    /** 
     * 关闭数据库连接  
     *  
     * @throws SQLException 
     */  
    public void closeConnection() throws SQLException {  
        if (null != result) {  
            result.close();  
        }  
        if (null != psmt) {  
            psmt.close();  
        }  
        if (null != con) {  
            con.close();  
        }  
    }  
    
    
    

    public static void InsertIN(String param) throws SQLException {  
        StringBuilder sql = new StringBuilder("INSERT INTO USER_MENU (USER_ID, MENU_ID) ").  
                append("VALUES (?,?)");
                //.  
                //append(" WHERE USER_ENTRY=?");   
        psmt = con.prepareStatement(sql.toString()); // 预编译SQL  
        psmt.setString(1, param); // 设置参数  
        psmt.setString(2, "58");
        psmt.executeUpdate(); // 执行SQL  
    }  
    
    
    
    
    
   
    
    
    public static void main(String[] args) throws Exception {
    	setUpConnection(); // 建立连接  
        query(); // 执行查询  
        printResult(); // 打印查询结果  
//    	for (int i = 0; i < dismeunIdList.size(); i++) {
//    		 InsertIN(dismeunIdList.get(i)+"");
//		}
    	System.out.println(dismeunIdList.size());
	}
    
}
