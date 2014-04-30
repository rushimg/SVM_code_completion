static void fibonacci() {
    int ptr1 = 1, ptr2 = 1, ptr3 = 0;
    int temp = 0;
    BufferedReader Data=new BufferedReader (new InputStreamReader(System.in));
   try {
        System.out.println("The Number Value's fib you required ? ");
        ptr3 = Integer.parseInt(Data.readLine());

    System.out.print(ptr1 + " " + ptr2 + " ");
    for (int i = 0; i < ptr3; i++) {
        System.out.print(ptr1 + ptr2 + " ");
        temp = ptr1;
        ptr1 = ptr2;
        ptr2 = temp + ptr2;
    }
   }catch(IOException err){
     System.out.println("Error!" + err);
 }catch(NumberFormatException err){
     System.out.println("Invald Input!");
 }
}
