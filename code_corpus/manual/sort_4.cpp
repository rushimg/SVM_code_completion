void SortAlgo::quickSort(int* data, int const len)
{
  int const lenD = len;
  int pivot = 0;
  int ind = lenD/2;
  int i,j = 0,k = 0;
  if(lenD>1){
    int* L = new int[lenD];
    int* R = new int[lenD];
    pivot = data[ind];
    for(i=0;i<lenD;i++){
      if(i!=ind){
        if(data[i]<pivot){
          L[j] = data[i];
          j++;
        }
        else{
          R[k] = data[i];
          k++;
        }
      }
    }
    quickSort(L,j);
    quickSort(R,k);
    for(int cnt=0;cnt<lenD;cnt++){
      if(cnt<j){
        data[cnt] = L[cnt];;
      }
      else if(cnt==j){
        data[cnt] = pivot;
      }
      else{
        data[cnt] = R[cnt-(j+1)];
      }
    }
  }
}
