public static List<Integer> bubbleSort(final List<Integer> list) {
    if (list.isEmpty())
        return list;

    int lastSwapIndex = 0;
    for (int i = 1; i < list.size(); i++) {
        if (list.get(i - 1) > list.get(i)) { //slow if no random access list
            final int temp = list.get(i - 1);
            list.set(i - 1, list.get(i));
            list.set(i, temp);
            lastSwapIndex = i;
        }
    }
    final List<Integer> result = bubbleSort(new ArrayList<>(list.subList(0, lastSwapIndex)));
    result.addAll(list.subList(lastSwapIndex, list.size()));
    return result;
}
