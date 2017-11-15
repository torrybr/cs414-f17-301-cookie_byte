package BackendTest;

import static org.junit.Assert.*;

import org.junit.Test;
import Backend.User;
import Backend.Invite;
/*
public class InviteTest {

	@Test
	public void testInvite()
	{

		User to = new User("a", "pass1", "a@a.a");
		User from = new User("b", "pass2", "b@b.b");
		
		Invite i = new Invite(to, from, 123);
		// If pass, invite created successfully
		assertTrue(to.getInvites().contains(i));

	}

	//TODO Check for users in game instead of games with gameID . Create a third users , both invites will involve one of users so you dont have to check gameID

	@Test
	public void testAcceptInvite()
	{
		User to = new User("a", "pass1", "a@a.a");
		User from = new User("b", "pass2", "b@b.b");
		
		// First invite sent and accepted
		
		Invite i = new Invite(to, from, 123);
		i.acceptInvite();
		
		assertTrue(to.getInvites().size() == 0);
		assertEquals(to.getCurrentGames().get(0), from.getCurrentGames().get(0));
		assertEquals(123, to.getCurrentGames().get(0).getGameID());
		
		// Second invite sent and accepted
		
		Invite i1 = new Invite(to, from, 111);
		i1.acceptInvite();
		
		assertTrue(to.getInvites().size() == 0);
		assertEquals(to.getCurrentGames().get(1), from.getCurrentGames().get(1));
		assertEquals(111, to.getCurrentGames().get(1).getGameID()); // This appears to be failing due to system saving current games twice each time. i.e. I add a game to user a, and that game is in ArrayList position 0 AND 1. Next game added takes up positions 2 AND 3
		
	}
	@Test
	public void testDeclineInvite()
	{
		User to = new User("a", "pass1", "a@a.a");
		User from = new User("b", "pass2", "b@b.b");
		
		Invite i = new Invite(to, from, 123);
		
		assertTrue(to.getInvites().contains(i));
		
		i.declineInvite();
		
		assertTrue(!to.getInvites().contains(i));
		assertTrue(to.getCurrentGames().isEmpty());
		
	}
	@Test
	public void testEquals()
	{
		User to = new User("a", "pass1", "a@a.a");
		User from = new User("b", "pass2", "b@b.b");
		
		Invite i = new Invite(to, from, 123);
		Invite i1 = new Invite(to, from, 123);
		Invite i2 = new Invite(to, from, 222);
		
		assertTrue(i.equals(i1));
		assertFalse(i.equals(i2));
	}

}
<<<<<<< Updated upstream
*/
=======
*/
>>>>>>> Stashed changes
