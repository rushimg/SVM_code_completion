
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            if (accessibleAWTComponentHandler != null) {
                Component.this.removeComponentListener(accessibleAWTComponentHandler);
                accessibleAWTComponentHandler = null;
            }
            if (accessibleAWTFocusHandler != null) {
                Component.this.removeFocusListener(accessibleAWTFocusHandler);
                accessibleAWTFocusHandler = null;
            }
            super.removePropertyChangeListener(listener);
        }

        // AccessibleContext methods
        //
        