
    private void doSwingSerialization() {
        Package swingPackage = Package.getPackage("javax.swing");
        // For Swing serialization to correctly work Swing needs to
        // be notified before Component does it's serialization.  This
        // hack accomodates this.
        //
        // Swing classes MUST be loaded by the bootstrap class loader,
        // otherwise we don't consider them.
        for (Class klass = Component.this.getClass(); klass != null;
                   klass = klass.getSuperclass()) {
            if (klass.getPackage() == swingPackage &&
                      klass.getClassLoader() == null) {
                final Class swingClass = klass;
                // Find the first override of the compWriteObjectNotify method
                Method[] methods = (Method[])AccessController.doPrivileged(
                                                                           new PrivilegedAction() {
                                                                               public Object run() {
                                                                                   return swingClass.getDeclaredMethods();
                                                                               }
                                                                           });
                for (int counter = methods.length - 1; counter >= 0;
                     counter--) {
                    final Method method = methods[counter];
                    if (method.getName().equals("compWriteObjectNotify")){
                        // We found it, use doPrivileged to make it accessible
                        // to use.
                        AccessController.doPrivileged(new PrivilegedAction() {
                                public Object run() {
                                    method.setAccessible(true);
                                    return null;
                                }
                            });
                        // Invoke the method
                        try {
                            method.invoke(this, (Object[]) null);
                        } catch (IllegalAccessException iae) {
                        } catch (InvocationTargetException ite) {
                        }
                        // We're done, bail.
                        return;
                    }
                }
            }
        }
    }

    