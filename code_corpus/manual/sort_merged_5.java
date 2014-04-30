//http://stackoverflow.com/questions/5958169/how-to-merge-two-sorted-arrays-into-a-sorted-array

import java.util.Arrays;

public class MergeTwoArrays {

    static int[] arr1=new int[]{1,3,4,5,7,7,9,11,13,15,17,19};
    static int[] arr2=new int[]{2,4,6,8,10,12,14,14,16,18,20,22};

    public static void main(String[] args){
        int FirstArrayLocation =0 ;
        int SecondArrayLocation=0;
        int[] mergeArr=new int[arr1.length + arr2.length];

        for ( int i=0; i<= arr1.length + arr2.length; i++){
            if (( FirstArrayLocation < arr1.length ) && (SecondArrayLocation < arr2.length)){
                if ( arr1[FirstArrayLocation] <= arr2[SecondArrayLocation]){
                    mergeArr[i]=arr1[FirstArrayLocation];
                    FirstArrayLocation++;
                }else{
                    mergeArr[i]=arr2[SecondArrayLocation];
                    SecondArrayLocation++;
                }
            }
            else if(SecondArrayLocation < arr2.length){
                    mergeArr[i]=arr2[SecondArrayLocation];
                    SecondArrayLocation++;
            }else if ( FirstArrayLocation < arr1.length ){
                    mergeArr[i]=arr1[FirstArrayLocation];
                    FirstArrayLocation++;
            }
        }
    }
}


