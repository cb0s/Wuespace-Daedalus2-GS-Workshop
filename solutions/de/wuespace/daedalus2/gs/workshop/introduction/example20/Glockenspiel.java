package de.wuespace.daedalus2.gs.workshop.introduction.example20;

public class Glockenspiel {
	
	public static class Gloeckner extends Thread {
		
		private final String msg;
		
		public Gloeckner(String msg) {
			this.msg = msg;
		}
		
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println(msg);
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// You don't need to worry about this...
				}
			}
			System.out.printf("--- %s-Thread finished ---%n", msg);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread ding = new Gloeckner("Ding");
		Thread dang = new Gloeckner("Dang");
		Thread dong = new Gloeckner("Dong");
		
		ding.start();
		dang.start();
		dong.start();
		
		System.out.println("--- Main-Thread finished ---");
	}
}
