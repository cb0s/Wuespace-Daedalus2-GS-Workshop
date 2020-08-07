package de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker;

/**
 * An Exception which will be thrown if the returned code of a cracked {@link Safe} is not correct during benchmarking.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see SafeCracker
 * @see Safe
 * @see Benchmarker
 */
public class CrackingException extends Exception {

	/**
	 * SerialVersionUID of CrackingException which will be thrown if a given Cracker does not work properly during
	 * benchmarking.
	 */
	private static final long serialVersionUID = -8867541195701032647L;

}
