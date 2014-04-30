public static void toObject(int[] intArray) {
 
		Integer[] result = new Integer[intArray.length];
		for (int i = 0; i < intArray.length; i++) {
			result[i] = Integer.valueOf(intArray[i]);
		}
	}
