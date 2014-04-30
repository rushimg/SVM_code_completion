
    public void enableInputMethods(boolean enable) {
        if (enable) {
            if ((eventMask & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0)
                return;

            // If this component already has focus, then activate the
            // input method by dispatching a synthesized focus gained
            // event.
            if (isFocusOwner()) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    FocusEvent focusGainedEvent =
                        new FocusEvent(this, FocusEvent.FOCUS_GAINED);
                    inputContext.dispatchEvent(focusGainedEvent);
                }
            }

            eventMask |= AWTEvent.INPUT_METHODS_ENABLED_MASK;
        } else {
            if ((eventMask & AWTEvent.INPUT_METHODS_ENABLED_MASK) != 0) {
                InputContext inputContext = getInputContext();
                if (inputContext != null) {
                    inputContext.endComposition();
                    inputContext.removeNotify(this);
                }
            }
            eventMask &= ~AWTEvent.INPUT_METHODS_ENABLED_MASK;
        }
    }

    