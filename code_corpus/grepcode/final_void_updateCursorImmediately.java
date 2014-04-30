
    final void updateCursorImmediately() {
        if (peer instanceof LightweightPeer) {
            Container nativeContainer = getNativeContainer();

            if (nativeContainer == null) return;

            ComponentPeer cPeer = nativeContainer.getPeer();

            if (cPeer != null) {
                cPeer.updateCursorImmediately();
            }
        } else if (peer != null) {
            peer.updateCursorImmediately();
        }
    }

    