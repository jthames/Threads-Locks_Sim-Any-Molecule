/*
 * 
 * CLASS: AtomGenerator.java
 * 
 * PURPOSE: This class creates a new Atom thread each random interval of time
 * 
 */
import java.util.Random;

public class AtomGenerator implements Runnable {
	
	private Random random = new Random();
	private int loc;
	private MoleculeComponent mc[];
	int atomCount = 1;
	
	public AtomGenerator(MoleculeComponent mc[], int loc){
		this.loc = loc;
		this.mc = mc;
	}
	
	@Override
	public void run() {
		while (true) {

			// Wait RANDOM seconds to create a new atom.
			try {
				Thread.sleep((long) (1000.0 * random.nextDouble()));
			} catch (InterruptedException e) {
				System.out.println("Error in MoleculeComp Sleep.");
				e.printStackTrace();
			}

			// New instance of an atom thread
			Atom atom = new Atom(mc, loc, atomCount++, mc[loc].name);
			Thread thread = new Thread(atom);
			thread.start();

		}
	}
}
