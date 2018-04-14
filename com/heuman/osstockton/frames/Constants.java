package com.heuman.osstockton.frames;

import java.util.concurrent.ThreadLocalRandom;

public class Constants {

	public static final char[] programNames = {'-', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	
	public static final int MAX_JOBS = 50;
	

	private Constants() {

	}

	public static int[] returnRandomNumArray(int _maxValue, int _length) {

		int[] tempArray = new int[_length];

		for (int i = 0; i < _length; i++)
			tempArray[i] = ThreadLocalRandom.current().nextInt(1, _maxValue + 1);

		return tempArray;
	}

}
