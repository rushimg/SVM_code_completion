public static void getMinValue(int[] array){  
     int minValue = array[0];  
     for(int i=1;i<array.length;i++){  
     if(array[i] < minValue){  
     minValue = array[i];  
        }  
     }  
}  
