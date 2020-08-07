package de.wuespace.daedalus2.gs.workshop.introduction.example1;

public class ThreadCreation {
	public static void main(String[] args) {
		
		// Possibility 1:
		//  Create a runnable
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hi I am in a Runnable!");
			}
		};
		
		//  Create Thread with runnable
		Thread t = new Thread(r);
		
		//  To start Thread use .start() (NOT .run() which just executes the Runnable in the main-thread)
		t.start();
		//  NOT t.run();
		
		
		// Possibility 2:
		//  Extend Thread class
		class ThreadExtension extends Thread {
			@Override
			public void run() {
				System.out.println("Hi I am in a Thread!");
			}
		}
		//  Again create Thread
		Thread t2 = new ThreadExtension();
		//  Start Thread
		t2.start();
		
		
		// (Possibility 3):
		//  Create Thread with Runnable anonymous inner type
		Thread t3 = new Thread(() -> {
			System.out.println("I am in a Thread, too!");
		});
		//  Start Thread
		t3.start();
	}
}
