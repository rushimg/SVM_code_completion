/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. Some graphs do not allow
     * edge-multiplicity. In such cases, if the graph already contains an edge
     * from the specified source to the specified target, than this method does
     * not change the graph and returns <code>null</code>.
     *
     * <p>The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.</p>
     *
     * <p>This method creates the new edge <code>e</code> using this graph's
     * <code>EdgeFactory</code>. For the new edge to be added <code>e</code>
     * must <i>not</i> be equal to any other edge the graph (even if the graph
     * allows edge-multiplicity). More formally, the graph must not contain any
     * edge <code>e2</code> such that <code>e2.equals(e)</code>. If such <code>
     * e2</code> is found then the newly created edge <code>e</code> is
     * abandoned, the method leaves this graph unchanged returns <code>
     * null</code>.</p>
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return The newly created edge if added to the graph, otherwise <code>
     * null</code>.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is <code>
     * null</code>.
     *
     * @see #getEdgeFactory()
     */