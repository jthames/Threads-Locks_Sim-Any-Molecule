/*
 * 
 * CLASS: ChemicalBondingCreator
 * 
 * FUNCTION: Continually check if there are enough semaphore permits for each element to create 
 * a new molecule. 
 * 
 * UPDATE: This program will include functionality to simulate the creation of any molecule.
 * 
 */


public class ChemicalBondingCreator implements Runnable {
	
	private MoleculeComponent mc[];
	public  int               count = 1;
	public  int               mcLength;
	public  String            moleculeName;
	
	
	public ChemicalBondingCreator(int l, MoleculeComponent[] m, String s)	{
		this.mcLength = l;
		this.mc = m;
		this.moleculeName = s;
	}

	@Override
	public void run() {
		
		while (true) {
			
			System.out.println("C.B.C.: looking for bonding");
			
			// Loop length of mc array which is # of unique atoms.
			// Each iteration acquires "number" value of necessary semaphore permits
			for(int i=0; i<mcLength; i++){
				
				System.out.println("C.B.C.: Waiting to acquire " + mc[i].number + " permits for " +
						mc[i].name);
				
				for(int j=0; j<mc[i].number; j++){
					
					try {
						mc[i].sem.acquire();
					} catch (InterruptedException e) {
						System.out.println("OOPS! Error in C.B.C. trying to acquire permit.");
						e.printStackTrace();
					}
					
					
				}
				System.out.println("C.B.C.: Acquired all " + mc[i].name +" permits." );
			}
			
			// At this point to program has successfully created one molecule.
			System.out.println("C.B.C.: " + moleculeName + " #" + count++ + " created!\n");
			
		}// end while
	}// end run()
} // end ChemicalBondingCreator class
