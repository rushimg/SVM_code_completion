
    public String getName() {
        if (name == null && !nameExplicitlySet) {
            synchronized(getObjectLock()) {
                if (name == null && !nameExplicitlySet)
                    name = constructComponentName();
            }
        }
        return name;
    }

    