package de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.Safe;

/**
 * A template for a simple SafeCracker like the {@link }.</br>
 * Provides all necessary details to create a custom {@link SafeCracker}.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see Safe
 */
public interface SafeCracker {
	/**
	 * Cracks the given {@link Safe} and returns its code.
	 * 
	 * @return the cracked code of the given {@link Safe}
	 */
	public long crack(Safe safe);
}
