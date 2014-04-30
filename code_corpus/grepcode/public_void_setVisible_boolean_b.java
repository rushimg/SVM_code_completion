
        public void setVisible(boolean b) {
            boolean old = Component.this.isVisible();
            Component.this.setVisible(b);
            if (b != old) {
                if (accessibleContext != null) {
                    if (b) {
                        accessibleContext.firePropertyChange(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AccessibleState.VISIBLE);
                    } else {
                        accessibleContext.firePropertyChange(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             AccessibleState.VISIBLE, null);
                    }
                }
            }
        }

        