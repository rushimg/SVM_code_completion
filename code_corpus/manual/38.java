static void readTextFromURL( String urlString ) throws IOException {

      /* Open a connection to the URL, and get an input stream
           for reading data from the URL. */

      URL url = new URL(urlString);
      URLConnection connection = url.openConnection();
      InputStream urlData = connection.getInputStream();

      /* Check that the content is some type of text.  Note: If 
         getContentType() method were called before getting  the input 
         stream, it is possible for contentType to be null only because 
         no connection can be made.  The getInputStream() method will 
         throw an error if no connection can be made. */

      String contentType = connection.getContentType();
      if (contentType == null || contentType.startsWith("text") == false)
         throw new IOException("URL does not seem to refer to a text file.");

      /* Copy lines of text from the input stream to the screen, until
           end-of-file is encountered  (or an error occurs). */
      
      BufferedReader in;  // For reading from the connection's input stream.
      in = new BufferedReader( new InputStreamReader(urlData) );

      while (true) {
         String line = in.readLine();
         if (line == null)
            break;
         System.out.println(line);
      }

   } // end readTextFromURL()
