package de.wuespace.daedalus2.gs.workshop.introduction.example5;

public class ThreadSynchro {
	
	// You will learn that there is an other way, too... (Keyword: volatile)
	private static int val;
	
	private static Object lock;
	private static volatile boolean locked;
	
	private static /*synchronized*/ void addToVal(int add) {
		val += add;
		System.out.println("New val after adding " + add + ": " + val);
	}
	
	public static void main(String[] args) throws InterruptedException {
		// Synchronizing Threads can be done in many different ways
		// Approach 1: Use the synchronized keyword on methods/functions and attributes
		Thread adder1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				addToVal(i);
				try {
					Thread.sleep(4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		adder1.start();
		
		Thread adder2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				addToVal(i);
				try {
					Thread.sleep(4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		adder2.start();
		
		adder1.join();
		adder2.join();
		
		// Approach 2: Use complex Object wait() and notify() patterns
		System.out.println("\n------- Round 2 :) -------\n");
		val = 0;
		lock = new Object();
		locked = false;
		Thread t1 = new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					if (locked) {
						synchronized(lock) {
							lock.wait();
						}
					}
					locked = true;
					addToVal(i);
					synchronized(lock) {
						lock.notify();
					}
					locked = false;
					Thread.sleep(4);
				}
			} catch(InterruptedException e) {}
		});
		t1.start();
		
		Thread t2 = new Thread(() -> {
			try {
				for (int i = 0; i < 10; i++) {
					if (locked) {
						synchronized(lock) {
							lock.wait();
						}
					}
					locked = true;
					addToVal(i);
					synchronized(lock) {
						lock.notify();
					}
					locked = false;
					Thread.sleep(4);
				}
			} catch(InterruptedException e) {}
		});
		t2.start();
		
		// Creating Locks... Using volatile resources... Avoiding Deadlocks...
		// Seems complicated, right? It really is no fun experience managing all Threads in a program.
		// This in mind the java.util.concurrent package was created with all its benefits.
		// For more information on this topic either read the Java-Docs or use a tutorial like:
		// http://tutorials.jenkov.com/java-concurrency/index.html
	}
}
