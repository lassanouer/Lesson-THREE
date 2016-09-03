package com.training.log.processor.commons;

public class Constants {
	private Constants() {

	}

	/*
	 * Numero -----------------------------------
	 */
	public static final int sMoinUn = -1;
	public static final int sZero = 0;
	public static final int sUn = 1;
	public static final int sDeux = 2;
	public static final int sTrois = 3;
	public static final int sQuatr = 4;
	public static final int sCinq = 5;
	public static final int sSis = 6;
	public static final int sSept = 7;
	public static final int sDix = 10;

	/*
	 * Pattern --------------------
	 */
	public static final String slog_entry_pattern = "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
}
