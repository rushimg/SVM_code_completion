
    final boolean areBoundsValid() {
        Container cont = getContainer();
        return cont == null || cont.isValid() || cont.getLayout() == null;
    }

    