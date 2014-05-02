
    private PropertyChangeSupport changeSupport;

    /*
     * In some cases using "this" as an object to synchronize by
     * can lead to a deadlock if client code also uses synchronization
     * by a component object. For every such situation revealed we should
     * consider possibility of replacing "this" with the package private
     * objectLock object introduced below. So far there're 3 issues known:
     * - CR 6708322 (the getName/setName methods);
     * - CR 6608764 (the PropertyChangeListener machinery);
     * - CR 7108598 (the Container.paint/KeyboardFocusManager.clearMostRecentFocusOwner methods).
     *
     * Note: this field is considered final, though readObject() prohibits
     * initializing final fields.
     