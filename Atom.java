/*
 * Atom.java
 * 
 */

public class Atom implements Runnable {

	private MoleculeComponent mc[];
	private int count, loc;
	public String name;
	
	public Atom(MoleculeComponent mc[], int loc, int count, String n) {
		this.mc = mc;
		this.count = count;
		this.name = n;
		this.loc = loc;
	}

	@Override
	public void run() {
		
		System.out.println(mc[loc].name + " " + count + " created and waiting for bonding");

		// This increases the total number of Carbon atom permits available in the cSem semaphore
		mc[loc].sem.release();

	}
}