package de.wuespace.daedalus2.gs.workshop.introduction.example4;

public class ThreadJoiner {
	public static void main(String[] args) throws InterruptedException {
		// After finishing a Thread "joins" the Main-Thread again.
		// To wait for this to happen you can always call Thread.join()
		// Thread just does nothing for 3 secs.
		Thread t = new Thread(() -> { try { Thread.sleep(3 * 1_000); } catch(InterruptedException e) {}});
		t.start();
		System.out.println("Waiting for Custom Thread to finish");
		t.join();	// Throws InterruptedException!
		System.out.println("Custom Thread joined Main-Thread");
		
		// Btw. if you want to prevent deadlocks by simple interrupting the join() process after some time add the time
		// you want the timeout to be
		// e.g. t.join(3_000); would only wait for 3_000 ms = 3 sec.
	}
}
