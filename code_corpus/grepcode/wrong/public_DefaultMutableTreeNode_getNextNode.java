
    public DefaultMutableTreeNode getNextNode() {
        if (getChildCount() == 0) {
            // No children, so look for nextSibling
            DefaultMutableTreeNode nextSibling = getNextSibling();

            if (nextSibling == null) {
                DefaultMutableTreeNode aNode = (DefaultMutableTreeNode)getParent();

                do {
                    if (aNode == null) {
                        return null;
                    }

                    nextSibling = aNode.getNextSibling();
                    if (nextSibling != null) {
                        return nextSibling;
                    }

                    aNode = (DefaultMutableTreeNode)aNode.getParent();
                } while(true);
            } else {
                return nextSibling;
            }
        } else {
            return (DefaultMutableTreeNode)getChildAt(0);
        }
    }


    