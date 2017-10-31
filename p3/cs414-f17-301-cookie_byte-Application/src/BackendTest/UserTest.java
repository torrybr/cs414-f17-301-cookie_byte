package BackendTest;

import static org.junit.Assert.*;

import org.junit.Test;

import Backend.User;

public class UserTest {

	@Test
	//testing constructor
	public void testUser() {
		User u = new User("A", "B", "C");
		assertEquals("A" , u.getUserID());
		assertEquals("B" , u.getPassword());
		assertEquals("C" , u.getEmail());
	}

}
