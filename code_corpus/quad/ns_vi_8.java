public void sum(int[] arr){
for(int i=0;i<arr.length;i++)
{
   for(int j=0;j<arr.length/2;j=+2)
   System.out.println(arr[i]+"+"+arr[j]+"+"+"="+(arr[i]+arr[j]));
   System.out.println(arr[i+1]+"+"+arr[j+1]+"+"+"="+(arr[i+1]+arr[j+1]));
}   
}//end of sum function
