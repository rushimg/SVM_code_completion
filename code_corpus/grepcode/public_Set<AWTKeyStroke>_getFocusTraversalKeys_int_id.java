
    public Set<AWTKeyStroke> getFocusTraversalKeys(int id) {
        if (id < 0 || id >= KeyboardFocusManager.TRAVERSAL_KEY_LENGTH - 1) {
            throw new IllegalArgumentException("invalid focus traversal key identifier");
        }

        return getFocusTraversalKeys_NoIDCheck(id);
    }

    // We define these methods so that Container does not need to repeat this
    // code. Container cannot call super.<method> because Container allows
    // DOWN_CYCLE_TRAVERSAL_KEY while Component does not. The Component method
    // would erroneously generate an IllegalArgumentException for
    // DOWN_CYCLE_TRAVERSAL_KEY.
    final void setFocusTraversalKeys_NoIDCheck(int id, Set<? extends AWTKeyStroke> keystrokes) {
        Set oldKeys;

        synchronized (this) {
            if (focusTraversalKeys == null) {
                initializeFocusTraversalKeys();
            }

            if (keystrokes != null) {
                for (Iterator iter = keystrokes.iterator(); iter.hasNext(); ) {
                    Object obj = iter.next();

                    if (obj == null) {
                        throw new IllegalArgumentException("cannot set null focus traversal key");
                    }

                    // Fix for 6195828:
                    //According to javadoc this method should throw IAE instead of ClassCastException
                    if (!(obj instanceof AWTKeyStroke)) {
                        throw new IllegalArgumentException("object is expected to be AWTKeyStroke");
                    }
                    AWTKeyStroke keystroke = (AWTKeyStroke)obj;

                    if (keystroke.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                        throw new IllegalArgumentException("focus traversal keys cannot map to KEY_TYPED events");
                    }

                    for (int i = 0; i < focusTraversalKeys.length; i++) {
                        if (i == id) {
                            continue;
                        }

                        if (getFocusTraversalKeys_NoIDCheck(i).contains(keystroke))
                        {
                            throw new IllegalArgumentException("focus traversal keys must be unique for a Component");
                        }
                    }
                }
            }

            oldKeys = focusTraversalKeys[id];
            focusTraversalKeys[id] = (keystrokes != null)
                ? Collections.unmodifiableSet(new HashSet(keystrokes))
                : null;
        }

        firePropertyChange(focusTraversalKeyPropertyNames[id], oldKeys,
                           keystrokes);
    }
    final Set getFocusTraversalKeys_NoIDCheck(int id) {
        // Okay to return Set directly because it is an unmodifiable view
        Set keystrokes = (focusTraversalKeys != null)
            ? focusTraversalKeys[id]
            : null;

        if (keystrokes != null) {
            return keystrokes;
        } else {
            Container parent = this.parent;
            if (parent != null) {
                return parent.getFocusTraversalKeys(id);
            } else {
                return KeyboardFocusManager.getCurrentKeyboardFocusManager().
                    getDefaultFocusTraversalKeys(id);
            }
        }
    }

    