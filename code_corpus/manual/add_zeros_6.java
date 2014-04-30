int iTest = 2;
    StringBuffer sTest = new StringBuffer("000000"); //if the string size is 6
    sTest.append(String.valueOf(iTest));
    System.out.println(sTest.substring(sTest.length()-6, sTest.length()));
