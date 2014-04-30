public static String[] SortA(String [] array){
         String tmp="";
  int index = 0 ;
  for (int i =0;i<array.length ;i++ ) 
        for (int j = i+1;j<array.length ;j++ ) {
              if(array[i].charAt(index)>array[j].charAt(index)){
                          tmp = array[i];
                          array[i] = array[j];
                          array[j] = tmp ;
              }//end of if
        }//end of loop
        index++;

        for (int x =0;x<array.length-1 ;x++ ){
              for (int y =x+1;y<array.length ;y++ ) {


              if(array[x].charAt(0)==array[y].charAt(0)){

                    if(array[x].charAt(index)>array[y].charAt(index)){
                          tmp = array[x];
                          array[x] = array[y];
                          array[y] = tmp ;

                    }

                    else if(array[x].charAt(index)==array[y].charAt(index)){
                    if(index<getSmallestRange(array))
                          index++;
                    }

              }//end of if
        }//end of loop
  }

  return array;
 }//end of method

