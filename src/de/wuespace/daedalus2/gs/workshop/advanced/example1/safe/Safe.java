package de.wuespace.daedalus2.gs.workshop.advanced.example1.safe;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.benchmark.DemoSafe;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCracker;

/**
 * A template for a simple Safe like the {@link SuperSecureSafe3000}.</br>
 * Provides all necessary details to create a custom {@link SafeCracker}.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see SuperSecureSafe3000
 * @see DemoSafe
 */
public interface Safe {
	/**
	 * Checks if a given code matches the current code of a Safe.
	 * 
	 * @param code : Code to check for validation
	 * @return <code>true</code> if given Code was correct, <code>false</code> otherwise
	 */
	public abstract boolean checkCode(long code);
	
	/**
	 * Tells how secure a safe's code is.</br>
	 * </br>
	 * The security-level is a synonym for the power of ten in which the code at least lays.</br>
	 * E.g.<ul>
	 * <li>security-level = 1: codes between 10 and 99</li>
	 * <li>security-level = 5: codes between 100_000 and 999_999</li>
	 * </ul>
	 * 
	 * @return
	 */
	public abstract int getSecurityLevel();
}
