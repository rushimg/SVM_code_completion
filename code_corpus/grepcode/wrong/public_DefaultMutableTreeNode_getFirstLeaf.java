
    public DefaultMutableTreeNode getFirstLeaf() {
        DefaultMutableTreeNode node = this;

        while (!node.isLeaf()) {
            node = (DefaultMutableTreeNode)node.getFirstChild();
        }

        return node;
    }


    