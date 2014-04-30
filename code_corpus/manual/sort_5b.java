public int[] merge(int[] L, int[] R){
  int lenL = L.length;
  int lenR = R.length;
  int[] merged = new int[lenL+lenR];
  int i = 0;
  int j = 0;
  while(i<lenL||j<lenR){
    if(i<lenL & j<lenR){
      if(L[i]<=R[j]){
        merged[i+j] = L[i];
        i++;
      }
      else{
        merged[i+j] = R[j];
        j++;
      }
    }
    else if(i<lenL){
      merged[i+j] = L[i];
      i++;
    }
    else if(j<lenR){
      merged[i+j] = R[j];
      j++;
     }
   }
   return merged;
}
