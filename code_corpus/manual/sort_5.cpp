void SortAlgo::mergeSort(int data[], int lenD)
{
  if(lenD>1){
    int middle = lenD/2;
    int rem = lenD-middle;
    int* L = new int[middle];
    int* R = new int[rem];
    for(int i=0;i<lenD;i++){
      if(i<middle){
        L[i] = data[i];
      }
      else{
        R[i-middle] = data[i];
      }
    }
    mergeSort(L,middle);
    mergeSort(R,rem);
    merge(data, lenD, L, middle, R, rem);
  }
}
