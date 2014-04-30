public static void sortNumbers(int[] list){
		//go through the list
		for (int i=0; i<list.length;i++){
			//define min
			int min = i;
			//go through the remaining list and see if there is smaller number
			for (int j=i+1;j<list.length;j++){
				//if there is a smaller number
				if (list[j] < list[min]){
					//remember its place
					min = j;
				}
			}
			if (i != min){
				//swap the min element, moving it to its proper place in the list.
				int temp = list[min];
				list[min] = list[i];
				list[i] = temp;
			}
		}
	}
