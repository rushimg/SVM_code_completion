
        public void setEnabled(boolean b) {
            boolean old = Component.this.isEnabled();
            Component.this.setEnabled(b);
            if (b != old) {
                if (accessibleContext != null) {
                    if (b) {
                        accessibleContext.firePropertyChange(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             null, AccessibleState.ENABLED);
                    } else {
                        accessibleContext.firePropertyChange(
                                                             AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                             AccessibleState.ENABLED, null);
                    }
                }
            }
        }

        