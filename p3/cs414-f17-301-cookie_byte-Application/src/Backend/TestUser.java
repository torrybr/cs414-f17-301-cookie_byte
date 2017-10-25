package Backend;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

//This class will test Invites and User because they are dependent on each other
public class TestUser {

	User usr1 = new User("usr1", "usr1", "usr1@fake.com", "abc123");
	User usr2 = new User("usr2", "usr2", "usr2@fake.com", "abc123");
	Game gme = new Game("1323", usr1, usr2);
	Game gme2 = new Game("1323", usr1, usr2);
	Game gme3 = new Game("1323", usr1, usr2);
	Game gme4 = new Game("1323", usr1, usr2);
	
	@Test
	public void testInvites(){
		
		//Accepting Condition
		usr1.sendInvitation(usr2); //Invite sent
		ArrayList <Invite>  i =  usr2.allInvitations(); 
		assertTrue(i.get(0).ToUser.equals(usr2));
		
		//User accepts
		usr2.responseInvitation(i.get(0), InvitationStatus.ACCECPTED);
		assertFalse(usr1.getCurrentGames().isEmpty()); //If game is now registered under user1
		assertFalse(usr2.getCurrentGames().isEmpty()); //If game is now registered under user2
		
		//Declining Condition
		usr2.sendInvitation(usr1); //Invite sent
		ArrayList <Invite>  i2 =  usr1.allInvitations(); //Checks its be received
		assertFalse(usr1.Invites.isEmpty()); //Checks its be received
		
		//User denies
		usr1.responseInvitation(i2.get(0), InvitationStatus.DECLINED);
		assertTrue(usr1.Invites.isEmpty()); //Checks if the denied invitation has been deleted
		
	}
	@Test
	public void testEquals(){
		
	//Test the 3 cases of the equals method
		
		//same
		assertTrue(usr1.equals(usr1));
		//different
		assertFalse(usr1.equals(usr2));
		//null
		assertFalse(usr1.equals(null));
	}
	@Test
	public void testWinPercentage(){
		
	//sets the winner of 4 fake games
		gme.setWinner(usr1);
		gme2.setWinner(usr1);
		gme3.setWinner(usr2);
		gme4.setWinner(usr2);
		
	//adds the fake games to the users past games list of both users
		
		usr1.PastGames.add(gme); usr2.PastGames.add(gme);
		usr1.PastGames.add(gme2); usr2.PastGames.add(gme2);
		usr1.PastGames.add(gme3); usr2.PastGames.add(gme3);
		usr1.PastGames.add(gme4); usr2.PastGames.add(gme4);
		
	//calculates the winPercentage should equal 50.00 for each player.
		
		usr1.winPercentage();
		usr2.winPercentage();
		
		assertTrue(usr1.getWinPercentage() == 50.00);
		assertTrue(usr2.getWinPercentage() == 50.00);
	}

}
