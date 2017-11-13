package UI;

import Database.DatabaseManagerImpl;
import Drivers.ClientDriver;

public class LoginAuth {
	DatabaseManagerImpl DBDriver;

	public boolean checkAuth(String username, String password) {
		ClientDriver cd = new ClientDriver();
		
		return cd.checkAuth(username, password);
	}
	public void deleteUser(ClientDriver driver){
		//db delete user
	}
}
