package de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.Safe;

/**
 * A demo {@link SafeCracker} which solves every Safe by simple Brute-Forcing.</br>
 * </br>
 * <i>As this {@link SafeCracker} is not making use of multithreading,
 * it does not scale particularly well with higher security-levels.</i>
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see SafeCracker
 */
public class SafeCrackerExample implements SafeCracker {

	@Override
	public long crack(Safe safe) {
		// Initialize code with the smallest safe-code possible
		long code = (long) Math.pow(10, safe.getSecurityLevel());
		
		// Check if code is correct, if not increase value by one and check again which should work on all correctly
		// implemented Safes
		while (!safe.checkCode(code)) {
			code++;
		}
		
		// Return correct code
		return code;
	}

}
