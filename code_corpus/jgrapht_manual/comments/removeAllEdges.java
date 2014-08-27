/**
     * Removes all the edges in this graph that are also contained in the
     * specified edge collection. After this call returns, this graph will
     * contain no edges in common with the specified edges. This method will
     * invoke the {@link #removeEdge(Object)} method.
     *
     * @param edges edges to be removed from this graph.
     *
     * @return <tt>true</tt> if this graph changed as a result of the call
     *
     * @throws NullPointerException if the specified edge collection is <tt>
     * null</tt>.
     *
     * @see #removeEdge(Object)
     * @see #containsEdge(Object)
     */