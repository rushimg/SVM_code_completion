public void sum(int[] arr){
  for(int i=0;i<arr.length;i++)
  {
    for(int j=i;j<arr.length;j++) //Note: j = i, not j = 0
      System.out.println(arr[i]+"+"+arr[j]+"+"+"="+(arr[i]+arr[j]));
  }   
}//end of sum function
