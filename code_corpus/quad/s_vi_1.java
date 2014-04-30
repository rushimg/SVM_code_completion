public static void sortNumbers(int[] list){
		for (int i=1; i<list.length -1; i++){
			int value = list[i];
			int j = i-1;
			while (j >= 0 && list[j] > value){
				list[j+1] = list[j];
				j--;
			}
			list[j+1] = value;
		}
	}
 
