/**
     * Removes all the edges going from the specified source vertex to the
     * specified target vertex, and returns a set of all removed edges. Returns
     * <code>null</code> if any of the specified vertices does not exist in the
     * graph. If both vertices exist but no edge is found, returns an empty set.
     * This method will either invoke the {@link #removeEdge(Object)} method, or
     * the {@link #removeEdge(Object, Object)} method.
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return the removed edges, or <code>null</code> if either vertex is not
     * part of graph
     */