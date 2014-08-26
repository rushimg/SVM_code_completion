 /**
     * Removes all the vertices in this graph that are also contained in the
     * specified vertex collection. After this call returns, this graph will
     * contain no vertices in common with the specified vertices. This method
     * will invoke the {@link #removeVertex(Object)} method.
     *
     * @param vertices vertices to be removed from this graph.
     *
     * @return <tt>true</tt> if this graph changed as a result of the call
     *
     * @throws NullPointerException if the specified vertex collection is <tt>
     * null</tt>.
     *
     * @see #removeVertex(Object)
     * @see #containsVertex(Object)
     */
