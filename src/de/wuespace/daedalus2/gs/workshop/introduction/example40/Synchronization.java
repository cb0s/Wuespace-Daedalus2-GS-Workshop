package de.wuespace.daedalus2.gs.workshop.introduction.example40;

public class Synchronization {
	
//	public static synchronized void addToTimer(int time) throws InterruptedException {
//		Thread.sleep(time);		// Threads need to be synchronized...
//	}
	
	public static void addToTimer(int time) throws InterruptedException {
		Thread.sleep(time);		// Does not work as expected...
	}
	
	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(() -> {
			try {
				System.out.println("T1 waiting for 5000 ms");
				addToTimer(5000);
				System.out.println("T1 watiing finished...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				System.out.println("T2 waiting for 2000 ms");
				addToTimer(2000);
				System.out.println("T2 waiting finished...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
		
		// Waits until t2 is finished...
		t2.join();
		System.out.println("Main Thread finished");
	}

}
