package com.java.theme;

import com.formdev.flatlaf.FlatLightLaf;

public class EasyCV extends FlatLightLaf {
	public static final String NAME = "EasyCV";

	public static boolean setup() {
		return setup( new EasyCV() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, EasyCV.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
