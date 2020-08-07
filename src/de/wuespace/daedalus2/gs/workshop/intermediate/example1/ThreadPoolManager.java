package de.wuespace.daedalus2.gs.workshop.intermediate.example1;

import java.util.HashMap;

/**
 * A simple Thread-Pool for learning a little bit about Threads.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see CustomThread
 */
public class ThreadPoolManager {
	public static void main(String[] args) throws InterruptedException {
		HashMap<Integer, CustomThread> threads = new HashMap<Integer, CustomThread>();
		for (int i = 0; i < 10; i++) {
			// TODO: Create 10 Custom Threads and put them into the Hashmap with i as key
		}
		
		// Stop Thread 3, 6 and ensure they are closed
		// TODO: Stop Thread 3, 6 and ensure they are stopped
		
		// Interrupt Thread 4... It is dangerous...
		// TODO: Interrupt Thread 4 and ensure it is dead
		
		// Stop all remaining Threads safely
		for (CustomThread c : threads.values()) {
			// TODO: Stop Threads and ensure they are stopped
		}
	}
}
