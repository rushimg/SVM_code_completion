
    public Object[] getUserObjectPath() {
        TreeNode[]          realPath = getPath();
        Object[]            retPath = new Object[realPath.length];

        for(int counter = 0; counter < realPath.length; counter++)
            retPath[counter] = ((DefaultMutableTreeNode)realPath[counter])
                               .getUserObject();
        return retPath;
    }

    