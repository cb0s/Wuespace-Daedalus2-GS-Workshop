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
			CustomThread t = new CustomThread(i+1);
			t.start();
			threads.put(i, t);
			Thread.sleep(400);
		}
		
		// Stop Thread 3, 6 and ensure they are closed
		threads.get(2).requestStop();
		threads.remove(2).join(1_500);
		
		threads.get(5).requestStop();
		threads.remove(5).join(1_500);
		
		// Interrupt Thread 4... It is dangerous...
		threads.get(3).interrupt();
		threads.remove(3).join(100);
		
		// Stop all remaining Threads safely
		for (CustomThread c : threads.values()) {
			c.requestStop();
			c.join(1_500);
		}
	}
}
