package de.wuespace.daedalus2.gs.workshop.advanced.example1.safe;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple implementation of {@link Safe}.</br>
 * This {@link Safe} does not serve any real purpose apart from being cracked.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see Safe
 */
public class SuperSecureSafe3000 implements Safe {

	/**
	 * Tells how secure a safe's code is.</br>
	 * </br>
	 * The security-level is a synonym for the power of ten in which the code at least lays.</br>
	 * E.g.<ul>
	 * <li>security-level = 1: codes between 10 and 99</li>
	 * <li>security-level = 5: codes between 100_000 and 999_999</li>
	 * </ul>
	 */
	private final int securityLevel = 8;
	
	/**
	 * The actual code for the Safe.</br>
	 * The only code for which {@link #checkCode(long)} will return <code>true</code>.
	 */
	private final long superSecureCode;
	
	/**
	 * Creates a new <i>random</i> secure code when called.
	 */
	public SuperSecureSafe3000() {
		// Creating Secure Code
		long secPow = (long) Math.pow(10, securityLevel);
		superSecureCode = ThreadLocalRandom.current().nextLong(secPow, secPow*10);
	}
	
	
	@Override
	public boolean checkCode(long code) {
		return code == superSecureCode;
	}

	@Override
	public int getSecurityLevel() {
		return securityLevel;
	}
}
