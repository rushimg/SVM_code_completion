
    public int getLevel() {
        TreeNode ancestor;
        int levels = 0;

        ancestor = this;
        while((ancestor = ancestor.getParent()) != null){
            levels++;
        }

        return levels;
    }


    