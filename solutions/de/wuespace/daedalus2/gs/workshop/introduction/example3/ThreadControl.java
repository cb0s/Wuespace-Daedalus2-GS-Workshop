package de.wuespace.daedalus2.gs.workshop.introduction.example3;

public class ThreadControl {
	public static void main(String[] args) throws InterruptedException {
		// Pause Thread... (without busy waiting! -> BAD)
		Thread t = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println((i+1));
				
				try {
					// ... with Thread.sleep();
					Thread.sleep(2 * 1_000);			// Pauses the Thread for 2_000 ms = 2 s
				} catch (InterruptedException e) { /* Will most likely not occur in this example */ }
			}
		});
		t.start();
		t.join();	// Do not worry about this: This will be explained in the next example
		
		// Stop Thread properly
		class CustomThread extends Thread {
			private boolean running = false;
			
			public void doStop() {
				running = false;
			}
			
			@Override
			public void run() {
				running = true;
				while (running) {
					System.out.println("We are still running");
					try {
						Thread.sleep(1_000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				running = false;
			}
		}
		
		CustomThread t2 = new CustomThread();
		t2.start();
		Thread.sleep(3 * 1_000);
		t2.doStop();
		t2.join();
		
		// Interrupt Thread -> "Gently" tells the Thread to be stopped
		// If Thread.sleep() [or other later discussed "waiting" methods] are active, an InterruptedException
		// or ClosedByInterruptException will be thrown depending on the usecase
		Thread t3 = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("Not responding");
			}
		});
		t3.start();
		Thread.sleep(3 * 1_000);
		t3.interrupt();
		t3.join();
		
		
		// EVIL CODE AHEAD...
		
		// Stop Thread -> bad habit (only use in special occasions e.g. the Thread is not responding)
		// Also Deprecated!
		// Usually means Thread is not properly developed...
		Thread tBad = new Thread(() -> { 
			while (true) System.out.println("Not responding"); 
		});
		tBad.start();
		Thread.sleep(3 * 1_000);
		tBad.stop();
	}
}
