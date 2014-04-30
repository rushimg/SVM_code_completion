private void merge(Integer[] a, Integer[] b) 
{
    int i = 0; int j = 0; int k = 0;

    while (i < a.length || j < b.length)
    {
        if (i != a.length && j != b.length)
        {
            if (a[i].compareTo(b[j]) <= 0)
            {
                this.array[k++] = a[i++];
            }
            else
            {
                this.array[k++] = b[j++];
            }
        }
        else if (i < a.length)
        {
            this.array[k++] = a[i++];
        }
        else if (j < b.length)
        {
            this.array[k++] = b[j++];
        }
    }
}
