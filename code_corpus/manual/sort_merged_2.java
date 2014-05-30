
public static int[] merge(int[] a, int[] b) {

    int[] answer = new int[a.length + b.length];
    int i = 0;
    int j = 0;
    int k = 0;

    while (i < a.length && j < b.length)
    {
        if (a[i] < b[j])       
            answer[k++] = a[i++];

        else        
            answer[k++] = b[j++];               
    }

    while (i < a.length)  
        answer[k++] = a[i++];


    while (j < b.length)    
        answer[k++] = b[j++];

    return answer;
}