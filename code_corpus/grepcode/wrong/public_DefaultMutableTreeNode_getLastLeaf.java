
    public DefaultMutableTreeNode getLastLeaf() {
        DefaultMutableTreeNode node = this;

        while (!node.isLeaf()) {
            node = (DefaultMutableTreeNode)node.getLastChild();
        }

        return node;
    }


    