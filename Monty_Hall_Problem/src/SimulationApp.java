/**
 * The goal of this program is to prove that it is better to switch doors in the
 * Monty Hall problem, rather than stay. We do so by running the simulation many times
 * using both strategies in order to see the effect. We assume the showmaster always opens
 * the door with a goat behind it (otherwise the game would be immediately over).
 *
 * @author Lukas Sogor
 */

public class SimulationApp {

	public static void main(String[] args) {
		
		int runs = 10000000;
		int victoriesStay = 0;
		int victoriesSwitch = 0;
		
		StayingPlayer _stay = new StayingPlayer();
		SwitchingPlayer _switch = new SwitchingPlayer();
		
		long startTime = System.nanoTime();
		Simulation[] simsStay = new Simulation[runs];
		for (int i = 0; i < runs; ++i) {
			Simulation sim = new Simulation(3, 1, _stay);
			sim.start();
			simsStay[i] = sim;
		}
		
		Simulation[] simsSwitch = new Simulation[runs];
		for (int i = 0; i < runs; ++i) {
			Simulation sim = new Simulation(3, 1, _switch);
			sim.start();
			simsSwitch[i] = sim;
		}
		long endTime = System.nanoTime();
		System.out.println("Duration sequentially: " + (endTime - startTime) / 1e06 + "ms");
		for (int i = 0; i < runs; ++i) {
			if (simsStay[i].hasWon()) {
				++victoriesStay;
			}
			if (simsSwitch[i].hasWon()) {
				++victoriesSwitch;
			}
		}
		double stayWinrate = (double) victoriesStay / runs * 100;
		double switchWinrate = (double) victoriesSwitch / runs * 100;
		
		System.out.println("StayingPlayer victories out of " + runs + " runs: " + victoriesStay + " -> " + stayWinrate + "%");
		System.out.println("SwitchingPlayer victories out of " + runs + " runs: " + victoriesSwitch + " -> " + switchWinrate + "%");
	}
}
