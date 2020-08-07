package de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.Safe;

/**
 * A demo {@link SafeCracker} which solves every Safe by simple Brute-Forcing with multithreading.</br>
 * </br>
 * <i>As this {@link SafeCracker} is not making use of balanced multithreading,
 * there might still be room for improvement.</i></br>
 * </br>
 * This is a possible solution for the given challenge
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see SafeCracker
 */
public class SafeCrackerMultiThreadingUnbalanced implements SafeCracker {
	
	/**
	 * The code which will be returned.</br>
	 * This attribute works as a payload between the main-thread and the workers.
	 */
	private volatile long code = 0;
	/**
	 * Indicates whether the correct code was found. This is important for the other Threads to stop their work.
	 */
	private volatile boolean found = false;
	/**
	 * Shows whether the {@link #lock} was entered.
	 */
	private volatile boolean locked = false;
	/**
	 * Is used for locking the Thread allocating the Lock while waiting for the Cracker to find the correct code.
	 */
	private Object lock;
	
	/**
	 * Constructor: Initializes lock.
	 */
	public SafeCrackerMultiThreadingUnbalanced() {
		lock = new Object();
	}
	
	@Override
	public long crack(Safe safe) {
		// Resetting variables
		found = false;
		locked = false;
		code = 0;
		
		for (int i = 0; i < 16; i++) {
			// Creating Thread
			final int j = i;
			Thread t = new Thread(() -> {
				long code = j + (long) Math.pow(10, safe.getSecurityLevel());
				long bounds = (code-j)*10;
				while (code < bounds && !safe.checkCode(code) && !found) {
					code += 16;
				}
				
				// Waking up Main-Thread and preparing payload
				if (safe.checkCode(code)) {
					this.code = code;
					found = true;
					if (locked)
						synchronized(lock) {
							lock.notifyAll();
						}
				}
			});
			t.start();
		}
		
		// Waiting for Workers to find result
		if (!found)
			synchronized (lock) {
				try {
					locked = true;
					lock.wait();
					locked = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		return code;
	}

}
