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
	private static Connection con = null; // ���ݿ����Ӷ���  
    private static PreparedStatement psmt = null; // Ԥ����������  
    private static ResultSet result = null;// ���������  
    private static List<Integer> dismeunIdList = new ArrayList<Integer>();
    /** 
     * �������ݿ����� 
     *  
     * @return 
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */  
    public static Connection setUpConnection() throws ClassNotFoundException, SQLException {  
        Class.forName("oracle.jdbc.driver.OracleDriver"); // ����Oracle��������  
        String url = "jdbc:oracle:thin:@" + "localhost:orcl"; // ����ʹ�õ�url  
        String user = "ROOT"; // ���ݿ��û���  
        String password = "ROOT"; // ����  
        con = DriverManager.getConnection(url, user, password);// ��ȡ����  
        return con;  
    }  
    
    
    /** 
     * ִ�в�ѯ 
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
        psmt = con.prepareStatement(sql.toString()); // Ԥ����SQL  
       // psmt.setString(1, "qtglcs"); // ���ò���  
        result = psmt.executeQuery(); // ִ��SQL  
        return result;  
    }  
    
    
    /** 
     * ������ѯ�������Ԫ������Ϣ 
     *  
     * @throws SQLException 
     */  
  /*  public static void analyzeResultSet() throws SQLException {  
        ResultSetMetaData rsmd = null;  
        StringBuilder sb = null;  
        rsmd = result.getMetaData();// ��ȡ��ѯ�α��Ԫ���ݶ���  
        sb = new StringBuilder();  
        System.out.println("����Ŀ��" + rsmd.getColumnCount());  
        for (int i = 0; i < rsmd.getColumnCount(); i++) {  
            sb.append("������" + rsmd.getColumnName(i + 1) + " ");  
            sb.append(rsmd.getColumnType(i + 1) + " ");  
            sb.append(rsmd.getColumnLabel(i + 1) + " ");  
            sb.append("������ȣ�" + rsmd.getColumnDisplaySize(i + 1) + "���ַ� ");  
            sb.append("�������ͣ�" + rsmd.getColumnTypeName(i + 1));  
            //System.out.println(sb.toString());  
            sb.delete(0, sb.length());  
        }  
    }  
    */
    
    /** 
     * ��ӡ��ѯ���Ľ�� 
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
     * �ر����ݿ�����  
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
        psmt = con.prepareStatement(sql.toString()); // Ԥ����SQL  
        psmt.setString(1, param); // ���ò���  
        psmt.setString(2, "58");
        psmt.executeUpdate(); // ִ��SQL  
    }  
    
    
    
    
    
   
    
    
    public static void main(String[] args) throws Exception {
    	setUpConnection(); // ��������  
        query(); // ִ�в�ѯ  
        printResult(); // ��ӡ��ѯ���  
//    	for (int i = 0; i < dismeunIdList.size(); i++) {
//    		 InsertIN(dismeunIdList.get(i)+"");
//		}
    	System.out.println(dismeunIdList.size());
	}
    
}
