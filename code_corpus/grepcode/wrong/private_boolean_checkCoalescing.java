
     private boolean checkCoalescing() {
         if (getClass().getClassLoader()==null) {
             return false;
         }
         final Class<? extends Component> clazz = getClass();
         synchronized (coalesceMap) {
             // Check cache.
             Boolean value = coalesceMap.get(clazz);
             if (value != null) {
                 return value;
             }

             // Need to check non-bootstraps.
             Boolean enabled = java.security.AccessController.doPrivileged(
                 new java.security.PrivilegedAction<Boolean>() {
                     public Boolean run() {
                         return isCoalesceEventsOverriden(clazz);
                     }
                 }
                 );
             coalesceMap.put(clazz, enabled);
             return enabled;
         }
     }

    