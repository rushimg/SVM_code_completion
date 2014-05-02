
    public void revalidate() {
        synchronized (getTreeLock()) {
            invalidate();

            Container root = getContainer();
            if (root == null) {
                // There's no parents. Just validate itself.
                validate();
            } else {
                while (!root.isValidateRoot()) {
                    if (root.getContainer() == null) {
                        // If there's no validate roots, we'll validate the
                        // topmost container
                        break;
                    }

                    root = root.getContainer();
                }

                root.validate();
            }
        }
    }

    