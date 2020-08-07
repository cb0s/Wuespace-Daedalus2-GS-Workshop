package de.wuespace.daedalus2.gs.workshop.intermediate.example1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Custom {@link Thread} implementation for the {@link ThreadPoolManager} Exercise.</br>
 * These Threads have a unique {@link #index} for identifying.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see ThreadPoolManager
 */
public class CustomThread extends Thread {
	
	private volatile boolean running;
	/**
	 * Index for identifying a {@link CustomThread}.</br>
	 * </br>
	 * <i>Note: The index should be unique though this will not be checked!</i>
	 */
	private final int index;
	
	/**
	 * Lock for blocking read access while writing.
	 */
	private final Lock lock;
	
	/**
	 * Creates a new {@link CustomThread} with an {@link #index}.</br>
	 * </br>
	 * <i>Note: The index should be unique though this will not be checked!</i>
	 * 
	 * @param index for the {@link CustomThread}
	 */
	public CustomThread(int index) {
		this.index = index;
		this.lock = new ReentrantLock(true);
	}
	
	/**
	 * Returns whether this Thread is running
	 * 
	 * @return if Thread is running
	 */
	public synchronized boolean isRunning() {
		lock.lock();
		lock.unlock();
		return running;
	}
	
	/**
	 * Requests to stop the Thread.
	 * 
	 * @throws IllegalStateException if Thread is not running
	 */
	public synchronized void requestStop() {
		if (running) {
			lock.lock();
			running = false;
			lock.unlock();
		} else
			throw new IllegalStateException("Thread is not running!");
	}
	
	/**
	 * Returns Id of the current Thread. 
	 */
	public int getIndex() {
		return index;
	}
	
	@Override
	public void run() {
		running = true;
		
		while(isRunning()) {
			System.out.println(index);
			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				System.out.println(index == 4 ? "Thread was dangerous" : "Hey you destroyed an innocent Thread!");
				running = false;
			}
		}
		
		System.out.println("Thread " + index + " left the chat...");
	}
}
