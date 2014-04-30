
        protected class AccessibleAWTFocusHandler implements FocusListener {
            public void focusGained(FocusEvent event) {
                if (accessibleContext != null) {
                    accessibleContext.firePropertyChange(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         null, AccessibleState.FOCUSED);
                }
            }
            public void focusLost(FocusEvent event) {
                if (accessibleContext != null) {
                    accessibleContext.firePropertyChange(
                                                         AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                                                         AccessibleState.FOCUSED, null);
                }
            }
        }  // inner class AccessibleAWTFocusHandler


        