 /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex <code>
     * u</code> such that <code>u.equals(v)</code>, the call removes all edges
     * that touch <code>u</code> and then removes <code>u</code> itself. If no
     * such <code>u</code> is found, the call leaves the graph unchanged.
     * Returns <tt>true</tt> if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * <p>If the specified vertex is <code>null</code> returns <code>
     * false</code>.</p>
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return <code>true</code> if the graph contained the specified vertex;
     * <code>false</code> otherwise.
     */
