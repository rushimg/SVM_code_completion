
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            if (accessibleAWTComponentHandler == null) {
                accessibleAWTComponentHandler = new AccessibleAWTComponentHandler();
                Component.this.addComponentListener(accessibleAWTComponentHandler);
            }
            if (accessibleAWTFocusHandler == null) {
                accessibleAWTFocusHandler = new AccessibleAWTFocusHandler();
                Component.this.addFocusListener(accessibleAWTFocusHandler);
            }
            super.addPropertyChangeListener(listener);
        }

        