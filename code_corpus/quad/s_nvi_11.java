public static void bubble_srt( int a[], int n ){
  int i, j,t=0;
  for(i = 0; i < n; i++){
  for(j = 1; j < (n-i); j++){
  if(a[j-1] > a[j]){
  t = a[j-1];
  a[j-1]=a[j];
  a[j]=t;
  }
  }
  }
  }
}

