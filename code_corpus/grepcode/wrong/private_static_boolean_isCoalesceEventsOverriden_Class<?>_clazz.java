
    private static boolean isCoalesceEventsOverriden(Class<?> clazz) {
        assert Thread.holdsLock(coalesceMap);

        // First check superclass - we may not need to bother ourselves.
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null) {
            // Only occurs on implementations that
            //   do not use null to represent the bootsrap class loader.
            return false;
        }
        if (superclass.getClassLoader() != null) {
            Boolean value = coalesceMap.get(superclass);
            if (value == null) {
                // Not done already - recurse.
                if (isCoalesceEventsOverriden(superclass)) {
                    coalesceMap.put(superclass, true);
                    return true;
                }
            } else if (value) {
                return true;
            }
        }

        try {
            // Throws if not overriden.
            clazz.getDeclaredMethod(
                "coalesceEvents", coalesceEventsParams
                );
            return true;
        } catch (NoSuchMethodException e) {
            // Not present in this class.
            return false;
        }
    }

    