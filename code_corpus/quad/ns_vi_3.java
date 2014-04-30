 public static void reverseArray(int[] data)
    {
        int[] reversedData = new int[data.length];
        int i;
        for(i=0; i < data.length; i++);
        {
            reversedData[i] = data[(data.length - i -1)];
        }
    }
