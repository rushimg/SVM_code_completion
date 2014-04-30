
    void checkGD(String stringID) {
        if (graphicsConfig != null) {
            if (!graphicsConfig.getDevice().getIDstring().equals(stringID)) {
                throw new IllegalArgumentException(
                                                   "adding a container to a container on a different GraphicsDevice");
            }
        }
    }

    