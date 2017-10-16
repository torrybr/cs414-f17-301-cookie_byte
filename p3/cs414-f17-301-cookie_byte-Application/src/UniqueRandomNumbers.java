import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//Random number generator 1 to a million.
public class UniqueRandomNumbers {

	public int numberGenerator() {
		ArrayList<Integer> list = new ArrayList<Integer>();
	    for (int i=1; i<1000000; i++) {
	        list.add(new Integer(i));
	    }
	    Collections.shuffle(list);
	    Random rand = new Random();
	    int selected = rand.nextInt(100000);
	        return (int)(list.get(selected));
	}
	
	public int offOrdef (){
		Random rand = new Random();
	    int selected = rand.nextInt(2);
	    return (selected);
	}
}
