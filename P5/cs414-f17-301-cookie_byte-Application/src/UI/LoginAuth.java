package UI;

import Drivers.ClientDriver;

public class LoginAuth {

	public boolean checkAuth(String username, String password) {
		ClientDriver cd = new ClientDriver();
		
		return cd.checkAuth(username, password);
	}
	public void deleteUser(ClientDriver driver){
		//db delete user
	}
}
