package BackendTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Backend.Invite;
import Backend.User;

public class UserTest {

	@Test
	//testing constructor
	public void testUser()
	{
		User u = new User("A", "B", "C");
		assertEquals("A" , u.getUserID());
		assertEquals("B" , u.getPassword());
		assertEquals("C" , u.getEmail());
	}
	
	@Test
	public void testOneAddInvite()
	{
		User u = new User("A", "B", "C");
		User u2 = new User("D", "E", "F");
		Invite i = new Invite(u, u2, 0);
		u.addInvite(i);
		for(int j = 0; j < u.getInvites().size(); j++) 
		{
		assertEquals(i, u.getInvites().get(j));
		}
	}

}
