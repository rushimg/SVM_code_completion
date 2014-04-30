
    public TreeNode getSharedAncestor(DefaultMutableTreeNode aNode) {
        if (aNode == this) {
            return this;
        } else if (aNode == null) {
            return null;
        }

        int             level1, level2, diff;
        TreeNode        node1, node2;

        level1 = getLevel();
        level2 = aNode.getLevel();

        if (level2 > level1) {
            diff = level2 - level1;
            node1 = aNode;
            node2 = this;
        } else {
            diff = level1 - level2;
            node1 = this;
            node2 = aNode;
        }

        // Go up the tree until the nodes are at the same level
        while (diff > 0) {
            node1 = node1.getParent();
            diff--;
        }

        // Move up the tree until we find a common ancestor.  Since we know
        // that both nodes are at the same level, we won't cross paths
        // unknowingly (if there is a common ancestor, both nodes hit it in
        // the same iteration).

        do {
            if (node1 == node2) {
                return node1;
            }
            node1 = node1.getParent();
            node2 = node2.getParent();
        } while (node1 != null);// only need to check one -- they're at the
        // same level so if one is null, the other is

        if (node1 != null || node2 != null) {
            throw new Error ("nodes should be null");
        }

        return null;
    }


    