
    int getAccessibleIndexInParent() {
        synchronized (getTreeLock()) {
            int index = -1;
            Container parent = this.getParent();
            if (parent != null && parent instanceof Accessible) {
                Component ca[] = parent.getComponents();
                for (int i = 0; i < ca.length; i++) {
                    if (ca[i] instanceof Accessible) {
                        index++;
                    }
                    if (this.equals(ca[i])) {
                        return index;
                    }
                }
            }
            return -1;
        }
    }

    