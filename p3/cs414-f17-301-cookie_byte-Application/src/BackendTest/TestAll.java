package BackendTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	//Runs all test classes
	UserTest.class,
	BoardTest.class,
	//InviteTest.class,
	PieceTest.class,
	SetBoardTest.class,
	GameControllerTest.class
	
})

public class TestAll {

}
