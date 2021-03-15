/**
 * Simulates the execution of the Monty Hall problem (once).
 * 
 * @author lukas
 *
 */

import java.util.Random;

public class Simulation {

	String[] doors;
	int numberOfDoors;
	int ferraris;
	Player player;
	private boolean success;
	boolean hasStarted;
	
	Simulation(int numberOfDoors, int ferraris, Player player) {
		this.numberOfDoors = numberOfDoors;
		this.ferraris = ferraris;
		this.player = player;
		doors = new String[numberOfDoors];
		String[] items = new String[numberOfDoors];
		for (int i = 0; i < numberOfDoors; ++i) { // fill with ferraris and goats
			if (ferraris > 0) {
				items[i] = "Ferrari";
				--ferraris;
			}
			else {
				items[i] = "Goat";
			}
		}
		Random rnd = new Random();
		for (int i = 0; i < numberOfDoors; ++i) {
			int index = rnd.nextInt(numberOfDoors);
			while (doors[index] != null) {
				index = rnd.nextInt(numberOfDoors);
			}
			doors[index] = items[i]; 
		}
	}
	
	/**
	 * Starts the simulation.
	 */
	void start() {
		this.hasStarted = true;
		Random rnd = new Random();
		int index = this.player.chooseDoor(this.numberOfDoors);
		int doorReveal = rnd.nextInt(this.numberOfDoors);
		while (doorReveal == index || doors[doorReveal].equals("Ferrari")) { // door different and has goat
			doorReveal = rnd.nextInt(this.numberOfDoors);
		}
		if (this.player instanceof SwitchingPlayer) {
			int oldIndex = index;
			while (index == oldIndex || index == doorReveal) { // new door must be unrevealed and not same as before
				index = rnd.nextInt(this.numberOfDoors);
			}
		}
		// at this point player has chosen his final door - was it the right one?
		this.success = winEval(index);
	}
	
	private boolean winEval(int index) {
		return doors[index].equals("Ferrari");
	}
	
	boolean hasWon() {
		if (this.hasStarted == false) {
			throw new IllegalStateException("Simulation has not been started yet!");
		}
		return this.success;
	}
}
