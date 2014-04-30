
    static boolean isInstanceOf(Object obj, String className) {
        if (obj == null) return false;
        if (className == null) return false;

        Class cls = obj.getClass();
        while (cls != null) {
            if (cls.getName().equals(className)) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }


    // ************************** MIXING CODE *******************************

    