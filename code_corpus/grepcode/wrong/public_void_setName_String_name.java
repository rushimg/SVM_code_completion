
    public void setName(String name) {
        String oldName;
        synchronized(getObjectLock()) {
            oldName = this.name;
            this.name = name;
            nameExplicitlySet = true;
        }
        firePropertyChange("name", oldName, name);
    }

    