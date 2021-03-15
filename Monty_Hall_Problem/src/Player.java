import java.util.Random;

public abstract class Player {
	
	int chooseDoor(int numberOfDoors) {
		Random rnd = new Random();
		return rnd.nextInt(numberOfDoors);
	}
}
