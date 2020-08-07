package de.wuespace.daedalus2.gs.workshop.advanced.example1;

import java.lang.reflect.InvocationTargetException;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.benchmark.Benchmarker;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.CrackingException;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.CustomCracker;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCracker;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCrackerExample;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.SuperSecureSafe3000;

/**
 * Main-Class for testing the different Safe-Crackers.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see CustomCracker
 * @see SafeCrackerExample
 */
public class WorkshopSafeCrackerMain {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, CrackingException {
		// NOTE: THIS CLASS DOES NOT NEED TO BE CHANGED!
		
		SafeCracker exampleCracker = new SafeCrackerExample();
		
		// TODO: Change CustomCracker
		SafeCracker sc = new CustomCracker();
		Benchmarker b = new Benchmarker();
		
		System.out.println("Showing performance of ExampleCracker:");
		b.benchmark(exampleCracker, 100, SuperSecureSafe3000.class);
		b.fullBenchmark(sc, 100, SuperSecureSafe3000.class);
	}
}
