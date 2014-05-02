
    public int getLeafCount() {
        int count = 0;

        TreeNode node;
        Enumeration enum_ = breadthFirstEnumeration(); // order matters not

        while (enum_.hasMoreElements()) {
            node = (TreeNode)enum_.nextElement();
            if (node.isLeaf()) {
                count++;
            }
        }

        if (count < 1) {
            throw new Error("tree has zero leaves");
        }

        return count;
    }


    //
    //  Overrides
    //

    