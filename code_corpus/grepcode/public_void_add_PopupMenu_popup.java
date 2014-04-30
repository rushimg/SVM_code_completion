
    public void add(PopupMenu popup) {
        synchronized (getTreeLock()) {
            if (popup.parent != null) {
                popup.parent.remove(popup);
            }
            if (popups == null) {
                popups = new Vector();
            }
            popups.addElement(popup);
            popup.parent = this;

            if (peer != null) {
                if (popup.peer == null) {
                    popup.addNotify();
                }
            }
        }
    }

    