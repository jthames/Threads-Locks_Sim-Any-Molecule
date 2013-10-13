/*
 * Joshua Thames
 * 
 * 4/3/2013
 *
 * PURPOSE: This program will simulate the natural production of any molecule.
 * 
 * NOTE :   Follow on screen prompts at the console. 
 * 			Program does not terminate but continues indefinitely. 
 */

import java.util.*;  
import java.util.concurrent.Semaphore;

public class Main {
	
	static public String moleculeName;
	
	public static void main(String[] args) {
		
		// Prompt user for inputs
		// Store variables
		// Figure out a way to create multiple semaphores
		// Begin code
		
		List<String> 			names 	 = 	new ArrayList<String>();
		List<Integer> 			numbers  = 	new ArrayList<Integer>();
		
		String name = "first";

		
		Scanner in = new Scanner(System.in);
		System.out.println("Input name of molecule to be simulated:");
	
		moleculeName = in.nextLine(); 
		    
		System.out.println("Begin typing in the names of the elements\n" + 
		                  " followed by the number required. Type 'done'\n" +
		                  " for name to finish the formula and begin" + 
							" the simulation. Maxsize = 10");
		
		System.out.println("\nFirst element name:");
		names.add(in.nextLine());
		
		System.out.println("\nQuantity of First Element:");
		numbers.add(in.nextInt());
		
		Semaphore sem[] = null;
		sem = new Semaphore[10];
		sem[0] = new Semaphore(0, true);
		
		MoleculeComponent mc[] = null;
		mc = new MoleculeComponent[10];
		mc[0] = new MoleculeComponent(sem[0], names.get(0), numbers.get(0));
		
		int i=1;
		while(true){
			
			System.out.println("Next Element Name in Formula:");
			name = in.next();
			if(name.equals("done") )
				break;
			names.add(name);
			
			System.out.println("That Element's Quantity in Formula");
			numbers.add(in.nextInt());
			
			sem[i] = new Semaphore(0, true);
			mc [i] = new MoleculeComponent(sem[i], names.get(i), numbers.get(i));
			
			i++;
		}
		
		// Print out summary to user to verify correct input.
		for(int j=0; j<i; j++)
			System.out.println("loc " + j + " Element Name = " + mc[j].name + " , Quantity = " + mc[j].number);
		System.out.println();
		in.close();  
		
		// Create the bonding class
		ChemicalBondingCreator cbc = new ChemicalBondingCreator(i, mc, moleculeName);
		Thread thread = new Thread(cbc);
		thread.start();
		
		AtomGenerator ag[] = new AtomGenerator[i];
		for(int j=0; j<i; j++){
			
			ag[j] = new AtomGenerator(mc, j);
			thread = new Thread(ag[j]);
			thread.start();
		}
		
	}

}
