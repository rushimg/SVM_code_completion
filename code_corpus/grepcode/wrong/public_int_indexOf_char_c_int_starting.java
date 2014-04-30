
    public int indexOf(char c, int starting) {
        int ret = indexOf(buff, start + starting, end, c);
        return (ret >= start) ? ret - start : -1;
    }

    