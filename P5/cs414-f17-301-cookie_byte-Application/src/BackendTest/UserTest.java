package BackendTest;

import Backend.GameController;
import Backend.Invite;
import Backend.User;
import Database.DatabaseManagerImpl;

import Database.UsersJavaObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

//	@Test
//	//testing constructor
//	public void testUser()
//	{
//		User u = new User("A", "B", "C");
//		assertEquals("A" , u.getUserID());
//		assertEquals("B" , u.getPassword());
//		assertEquals("C" , u.getEmail());
//	}
//	
//	@Test
//	//testing adding one invite
//	public void testAddOneInvite()
//	{
//		DatabaseManagerImpl DBDriver = new DatabaseManagerImpl();
//		UsersJavaObject temp = DBDriver.getUserByNickname("A");
//		User u = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
//		User u2 = new User("D", "E", "F");
//		Invite i = new Invite("A", u2, 0); //add invite will be called here
//		for(int j = 0; j < temp.getInvites().size(); j++) 
//		{
//		assertEquals(i, temp.getInvites().get(j));
//		}
//		assertEquals(1, temp.getInvites().size());
//	}
//	
//	@Test
//	//testing adding more than just one invite
//	public void testAddMultipleInvites()
//	{
//		User u = new User("A", "B", "C");
//		User u2 = new User("D", "E", "F");
//		List<Invite> invites = new ArrayList<Invite>();
//		Invite i = new Invite("A", u2, 0); //add invite will be called here
//		Invite i2 = new Invite("A", u2, 1); //add invite will be called here
//		Invite i3 = new Invite("A", u2, 2); //add invite will be called here
//		invites.add(i);
//		invites.add(i2);
//		invites.add(i3);
//		
//		for(int j = 0; j < u.getInvites().size(); j++) 
//		{
//		assertEquals(invites.get(j), u.getInvites().get(j));
//		}
//		assertEquals(3, u.getInvites().size());
//	}
//	
//	@Test
//	//testing removing only invite
//	public void testRemoveOnlyInvite()
//	{
//		User u = new User("A", "B", "C");
//		User u2 = new User("D", "E", "F");
//		Invite i = new Invite("A", u2, 0); //add invite will be called here
//		u.removeInvite(i);
//		assertEquals(0, u.getInvites().size());
//	}
//	
//	@Test
//	//testing removing multiple invites
//	public void testRemoveMultipleInvites()
//	{
//		DatabaseManagerImpl DBDriver = new DatabaseManagerImpl();
//		UsersJavaObject temp = DBDriver.getUserByNickname("A");
//		User u = new User(temp.getNickname(),temp.getPassword(),temp.getEmail());
//		User u2 = new User("D", "E", "F");
//		Invite i = new Invite("A", u2, 0); //add invite will be called here
//		Invite i2 = new Invite("A", u2, 1); //add invite will be called here
//		Invite i3 = new Invite("A", u2, 2); //add invite will be called here
//		
//		u.removeInvite(i);
//		u.removeInvite(i3);
//		
//		assertEquals(1, u.getInvites().size());
//		for(int j = 0; j < u.getInvites().size(); j++) 
//		{
//		assertEquals(i2, u.getInvites().get(j));
//		}
//		
//	}
//	
//	@Test
//	//test adding a past game
//	public void testAddPastGame() 
//	{
//		User u = new User("A", "B", "C");
//		GameController g = new GameController(0, u, u);
//		u.addPastGame(g);
//		assertEquals(1, u.getPastGames().size());
//		assertEquals(g, u.getPastGames().get(0));
//	}
//	
//	@Test
//	//test adding a current game
//	public void testAddCurrentGame() 
//	{
//		User u = new User("A", "B", "C");
//		User u2 = new User("D", "E", "F");
//		GameController g = new GameController(0, u, u2); //add current game called here
//		assertEquals(1, u.getCurrentGames().size());
//		assertEquals(g, u.getCurrentGames().get(0));
//	}
//	
//	@Test
//	//test removing a current game
//	public void testRemoveCurrentGame() 
//	{
//		User u = new User("A", "B", "C");
//		User u2 = new User("D", "E", "F");
//		GameController g = new GameController(0, u, u2); //add current game called here
//		u.removeCurrentGame(g);
//		assertEquals(0, u.getCurrentGames().size());
//	}
//	
//	@Test
//	//test adding a loss
//	public void testAddLoss() 
//	{
//		User u = new User("A", "B", "C");
//		u.addLoss();
//		assertEquals(1, u.getLosses());
//	}
//	
//	@Test
//	//test adding a win
//	public void testAddWin() 
//	{
//		User u = new User("A", "B", "C");
//		u.addWin();
//		assertEquals(1, u.getWins());
//	}

}
