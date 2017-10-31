package BackendTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	//testing adding one invite
	public void testAddOneInvite()
	{
		User u = new User("A", "B", "C");
		User u2 = new User("D", "E", "F");
		Invite i = new Invite(u, u2, 0); //add invite will be called here
		for(int j = 0; j < u.getInvites().size(); j++) 
		{
		assertEquals(i, u.getInvites().get(j));
		}
		assertEquals(1, u.getInvites().size());
	}
	
	@Test
	//testing adding more than just one invite
	public void testAddMultipleInvites()
	{
		User u = new User("A", "B", "C");
		User u2 = new User("D", "E", "F");
		List<Invite> invites = new ArrayList<Invite>();
		Invite i = new Invite(u, u2, 0); //add invite will be called here
		Invite i2 = new Invite(u, u2, 1); //add invite will be called here
		Invite i3 = new Invite(u, u2, 2); //add invite will be called here
		invites.add(i);
		invites.add(i2);
		invites.add(i3);
		
		for(int j = 0; j < u.getInvites().size(); j++) 
		{
		assertEquals(invites.get(j), u.getInvites().get(j));
		}
		assertEquals(3, u.getInvites().size());
	}
	
	@Test
	//testing removing only invite
	public void testRemoveOnlyInvite()
	{
		User u = new User("A", "B", "C");
		User u2 = new User("D", "E", "F");
		Invite i = new Invite(u, u2, 0); //add invite will be called here
		u.removeInvite(i);
		assertEquals(0, u.getInvites().size());
	}
	
	@Test
	//testing removing multiple invites
	public void testRemoveMultipleInvites()
	{
		User u = new User("A", "B", "C");
		User u2 = new User("D", "E", "F");
		Invite i = new Invite(u, u2, 0); //add invite will be called here
		Invite i2 = new Invite(u, u2, 1); //add invite will be called here
		Invite i3 = new Invite(u, u2, 2); //add invite will be called here
		
		u.removeInvite(i);
		u.removeInvite(i3);
		assertEquals(1, u.getInvites().size());
		for(int j = 0; j < u.getInvites().size(); j++) 
		{
		assertEquals(i2, u.getInvites().get(j));
		}
	}

}
