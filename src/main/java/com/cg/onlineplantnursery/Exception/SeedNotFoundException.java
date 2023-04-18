package com.cg.onlineplantnursery.Exception;

public class SeedNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeedNotFoundException(String message) {
		super(message);
	}
}