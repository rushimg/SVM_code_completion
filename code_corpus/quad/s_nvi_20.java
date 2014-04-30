public static void kSortMoidifyInput(int[] arr, int k) {
        Queue<Integer> queue = new PriorityQueue<Integer>(k + 1);
        for (int  i = 0; i <= k; i++) {
            queue.add(arr[i]);
        }
        int ctr = 0;
        for (int i = k + 1; i < arr.length; i++) {
            arr[ctr++] = queue.poll();
            queue.add(arr[i]);
        }

        while (!queue.isEmpty()) {
            arr[ctr++] = queue.poll();
        }
    }
