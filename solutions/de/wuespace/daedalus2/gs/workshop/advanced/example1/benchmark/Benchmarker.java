package de.wuespace.daedalus2.gs.workshop.advanced.example1.benchmark;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.CrackingException;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCracker;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCrackerExample;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.cracker.SafeCrackerMultiThreadingUnbalanced;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.Safe;
import de.wuespace.daedalus2.gs.workshop.advanced.example1.safe.SuperSecureSafe3000;

/**
 * A simple Benchmarker for {@link SafeCracker SafeCrackers}.</br>
 * To compare different results there is also the possibility of benchmarking the {@link DemoSafe}.
 * 
 * @author Cedric Boes
 * @version 1.0
 * @see Safe
 * @see SafeCracker
 */
public class Benchmarker {
	
	/**
	 * Specifies how many times a benchmark runs through by default and if no arguments were given.
	 */
	private int defaultNoSafes;
	
	/**
	 * Constructor. Creates a new {@link Benchmarker} with a final {@link #defaultNoSafes}.
	 */
	public Benchmarker() {
		defaultNoSafes = 50;
	}
	
	/**
	 * Checks how long a {@link SafeCracker} needs to crack the {@link DemoSafe}.</br>
	 * This is done to create a base of comparison between different PCs.
	 * 
	 * @param cracker
	 * @param noSafes
	 */
	public void evaluate(SafeCracker cracker, int noSafes) {
		System.out.printf("# Evaluating Computer-Performance with on Demo-Safe with %s...%n",
				cracker.getClass().getSimpleName().replace(".class", ""));
		long nanoTime = 0;
		long totalTime = 0;
		for (int i = 0; i < noSafes; i++) {
			nanoTime = System.nanoTime();
			cracker.crack(DemoSafe.getDefault());
			totalTime += System.nanoTime() - nanoTime;
			System.out.printf("Iteration %d:\t%d ns%n", i+1, System.nanoTime() - nanoTime);
		}
		System.out.println("-- Evaluation finished --");
		System.out.printf("Total ellapsed time: %d ns (%f s)%n%n", totalTime, totalTime / 1_000_000_000.0);
	}
	
	/**
	 * Checks how long a {@link SafeCracker} will need to crack a given {@link Safe} for a given amount of times.
	 * 
	 * @param cracker
	 * @param noSafes
	 * @param safe
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws CrackingException 
	 */
	public void benchmark(SafeCracker cracker, int noSafes, Class<? extends Safe> safe)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, CrackingException {
		System.out.printf("# Benchmarking %d times%n%n", noSafes);
		long nanoTime = 0;
		long totalTime = 0;
		for (int i = 0; i < noSafes; i++) {
			Safe s = safe.getDeclaredConstructor().newInstance();
			nanoTime = System.nanoTime();
			if (!s.checkCode(cracker.crack(s))) throw new CrackingException();
			totalTime += System.nanoTime() - nanoTime;
			System.out.printf("Iteration %d:\t%d ns%n", i+1, System.nanoTime() - nanoTime);
		}
		System.out.println("-- Evaluation finished --");
		System.out.printf("Total ellapsed time: %d ns (%f s)%n%n", totalTime, totalTime / 1_000_000_000.0);
	}
	
	/**
	 * Benchmarks <i>and</i> evaluates a SafeCracker.
	 * 
	 * @param cracker
	 * @param noSafes
	 * @param safe
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws CrackingException 
	 */
	public void fullBenchmark(SafeCracker cracker, int noSafes, Class<? extends Safe> safe)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, CrackingException {
		System.out.println("-------- Starting Full Benchmark --------\n");
		String s = MessageFormat.format("# Using SafeCracker [{0}] for safe [{1}]\n",
							cracker.getClass().getSimpleName().replace(".class", ""),
							safe.getSimpleName().replace(".class", ""));
		System.out.println(s);		

		evaluate(new SafeCrackerExample(), noSafes);
		evaluate(new SafeCrackerMultiThreadingUnbalanced(), noSafes);
		benchmark(cracker, noSafes, safe);
		
		System.out.println("-------- Benchmark completed --------");
	}
	
	/**
	 * Benchmarks a given {@link SafeCracker} with the {@link SuperSecureSafe3000} for the given amount of times.
	 * 
	 * @param cracker to be benchmarked
	 * @param noSafes no of random safes to test with
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws CrackingException 
	 */
	public void fullBenchmarkSuperSecureSafe3000(SafeCracker cracker, int noSafes)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, CrackingException {
		fullBenchmark(cracker, noSafes, SuperSecureSafe3000.class);
	}
	
	/**
	 * Benchmarks a given {@link SafeCracker} with the {@link SuperSecureSafe3000} for {@link #defaultNoSafes} times.
	 * </br></br>
	 * @see {@link #fullBenchmarkSuperSecureSafe3000(SafeCracker, int)}
	 * 
	 * @param cracker to be benchmarked
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws CrackingException 
	 */
	public void fullBenchmarkSafeCracker(SafeCracker cracker)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, CrackingException {
		fullBenchmarkSuperSecureSafe3000(cracker, defaultNoSafes);
	}
	
}
