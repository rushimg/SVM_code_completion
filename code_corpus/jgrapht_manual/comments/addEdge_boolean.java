/**
     * Adds the specified edge to this graph, going from the source vertex to
     * the target vertex. More formally, adds the specified edge, <code>
     * e</code>, to this graph if this graph contains no edge <code>e2</code>
     * such that <code>e2.equals(e)</code>. If this graph already contains such
     * an edge, the call leaves this graph unchanged and returns <tt>false</tt>.
     * Some graphs do not allow edge-multiplicity. In such cases, if the graph
     * already contains an edge from the specified source to the specified
     * target, than this method does not change the graph and returns <code>
     * false</code>. If the edge was added to the graph, returns <code>
     * true</code>.
     *
     * <p>The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.</p>
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     * @param e edge to be added to this graph.
     *
     * @return <tt>true</tt> if this graph did not already contain the specified
     * edge.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws ClassCastException if the specified edge is not assignment
     * compatible with the class of edges produced by the edge factory of this
     * graph.
     * @throws NullPointerException if any of the specified vertices is <code>
     * null</code>.
     *
     * @see #addEdge(Object, Object)
     * @see #getEdgeFactory()
     */