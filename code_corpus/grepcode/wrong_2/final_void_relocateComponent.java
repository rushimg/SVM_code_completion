
    final void relocateComponent() {
        synchronized (getTreeLock()) {
            if (peer == null) {
                return;
            }
            int nativeX = x;
            int nativeY = y;
            for (Component cont = getContainer();
                    cont != null && cont.isLightweight();
                    cont = cont.getContainer())
            {
                nativeX += cont.x;
                nativeY += cont.y;
            }
            peer.setBounds(nativeX, nativeY, width, height,
                    ComponentPeer.SET_LOCATION);
        }
    }

    