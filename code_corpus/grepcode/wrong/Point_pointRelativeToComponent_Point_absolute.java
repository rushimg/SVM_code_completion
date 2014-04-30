
    Point pointRelativeToComponent(Point absolute) {
        Point compCoords = getLocationOnScreen();
        return new Point(absolute.x - compCoords.x,
                         absolute.y - compCoords.y);
    }

    