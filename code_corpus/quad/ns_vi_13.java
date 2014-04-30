static void printDisp(int n[])
    {
        int i;
        int sum=0;
        <span class="skimlinks-unlinked">System.out.println("Given</span> Array elements : ");
        for (i=0; i<n.length; i++) 
        {
            <span class="skimlinks-unlinked">System.out.println(n</span>[i]);
            sum += n[i];
        }
        <span class="skimlinks-unlinked">System.out.println("Sum</span> of elements of the array = " + sum);
        <span class="skimlinks-unlinked">System.out.println</span>();
    }

