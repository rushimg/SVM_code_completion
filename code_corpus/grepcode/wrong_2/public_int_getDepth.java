
    public int getDepth() {
        Object  last = null;
        Enumeration     enum_ = breadthFirstEnumeration();

        while (enum_.hasMoreElements()) {
            last = enum_.nextElement();
        }

        if (last == null) {
            throw new Error ("nodes should be null");
        }

        return ((DefaultMutableTreeNode)last).getLevel() - getLevel();
    }



    