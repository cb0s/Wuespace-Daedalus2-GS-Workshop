package de.wuespace.daedalus2.gs.workshop.introduction.example2;

public class ThreadNames {
	@SuppressWarnings("unused")
	private static void withoutName() {
		Thread t = new Thread(() -> {
			System.out.println(" Do something");
		});
		System.out.println("Thread " + t.getName() + " will be executed");
		t.start();
	}
	
	@SuppressWarnings("unused")
	private static void withCustomName() {
		Thread t = new Thread(() -> {
			System.out.println(" Do something, too!");
		}, "Custom Thread");
		System.out.println("Thread " + t.getName() + " will be executed");
		t.start();
	}
	
	public static void main(String[] args) {
		System.out.println("Main-Thread: " + Thread.currentThread().getName());

		withoutName();
//		withCustomName();
	}
}
