/*
 * MoleculeComponent.java
 * 
 */


import java.util.concurrent.Semaphore;

public class MoleculeComponent {
	public Semaphore sem = new Semaphore(0, true);
	public String name;
	public int number;

	public MoleculeComponent(Semaphore sem, String s, int n){	
		this.sem = sem;
		this.name = s;
		this.number = n;
	}
	
	
}
