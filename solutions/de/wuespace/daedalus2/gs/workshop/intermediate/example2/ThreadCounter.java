package de.wuespace.daedalus2.gs.workshop.intermediate.example2;

public class ThreadCounter {
	
	private volatile static int count;
	
	private synchronized static boolean countUp(int countUp) {
		if (count + countUp <= 100) {
			count += countUp;
			System.out.println(count);
			return true;
		} else return false;
	}
	
	public static void main(String[] args) {
		// Your job is to count up to 100 as fast as possible using 2 Threads without having inconsistent data or
		// exceeding 100!
		// Thread 1 adds 1, Thread 2 adds 2
		
		Thread t1 = new Thread(() -> {
			System.out.println("T1 started");
			while (countUp(1));
			System.out.println("T1 stopped");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("T2 started");
			while (countUp(2));
			System.out.println("T2 stopped");
		});
		
		t1.start();
		t2.start();
	}
}
