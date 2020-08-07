package de.wuespace.daedalus2.gs.workshop.advanced.example1.benchmark;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.Safe;

/**
 * This is a demo {@link Safe} which is only a reference for the {@link Benchmarker}.</br>
 * It can only be used in a static context by getting the Default-Object. This was done because this safe always has
 * the same code so it would not make sense to have more than 1 object.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see Safe
 */
public class DemoSafe implements Safe {
	
	/**
	 * Default {@link DemoSafe}.</br>
	 * As this safe always has the same {@link #code} it is not useful to have more than 1 object.
	 */
	private final static DemoSafe singleton;
	
	static {
		singleton = new DemoSafe();
	}
	
	/**
	 * This is the code for the {@link DemoSafe}
	 */
	public final long code = 50_000_000;
	
	/**
	 * Block Objects. This class if for static use only!
	 */
	private DemoSafe() {}
	
	/**
	 * Returns the only existing Object of {@link DemoSafe}.
	 * 
	 * @return Only existing Object of {@link DemoSafe}
	 */
	public static DemoSafe getDefault() {
		return singleton;
	}
	
	@Override
	public boolean checkCode(long code) {
		return this.code == code;
	}

	@Override
	public int getSecurityLevel() {
		return 7;
	}

}
