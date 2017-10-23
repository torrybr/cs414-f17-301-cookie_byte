package Backend;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

//This class will test invites and user because they are dependent on each other
public class TestUser {

	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	
	@Test
	public void testInvites() {
		
		//Accepting Condition
		usr1.sendInvitation(usr2);
		ArrayList <Invite>  i =  usr2.allInvitations();
		assertTrue(i.get(0).ToUser.equals(usr2));
		usr2.responseInvitation(i.get(0), InvitationStatus.ACCECPTED);
		assertFalse(usr1.getCurrentGames().isEmpty());
		assertFalse(usr2.getCurrentGames().isEmpty());
		
		//Declining Condition
		usr2.sendInvitation(usr1);
		ArrayList <Invite>  i2 =  usr1.allInvitations();
		usr1.responseInvitation(i2.get(0), InvitationStatus.DECLINED);
		assertTrue(usr1.Invites.isEmpty());
		assertTrue(usr2.Invites.isEmpty());
	}

}
