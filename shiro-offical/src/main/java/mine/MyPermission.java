package mine;

import org.apache.shiro.authz.Permission;

public class MyPermission implements Permission{

	String permissionName;
	
	public MyPermission(){
		
	}
	
	public MyPermission(String permissionName){
		this.permissionName = permissionName;
	}
	
	public boolean implies(Permission p) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
