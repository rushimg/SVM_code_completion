static void towersOfHanoi(int disks, int from, int to, int spare) {
      if (disks == 1) {
         System.out.println("Move a disk from stack number "
                  + from + " to stack number " + to);
      }
      else {
         towersOfHanoi(disks-1, from, spare, to);
         System.out.println("Move a disk from stack number "
                  + from + " to stack number " + to);
         towersOfHanoi(disks-1, spare, to, from);
      }
   }
