
    static public final Enumeration<TreeNode> EMPTY_ENUMERATION
        = new Enumeration<TreeNode>() {
            public boolean hasMoreElements() { return false; }
            public TreeNode nextElement() {
                throw new NoSuchElementException("No more elements");
            }
    };

    