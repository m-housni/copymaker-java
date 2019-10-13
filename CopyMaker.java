// Provides for system input and output through data streams, serialization and the file system. Unless otherwise noted, passing a null argument to a constructor or method in any class or interface in this package will cause a NullPointerException to be thrown.
import java.io.*;


// A class is a user defined blueprint or prototype from which objects are created. It represents the set of properties or methods that are common to all objects of one type.
class CopyMaker
{
  String sourceName, destName;
  BufferedReader source;
  PrintWriter dest;
  String line;

  // A constructor in Java is a special method that is used to initialize objects. The constructor is called when an object of a class is created.
  CopyMaker ( String source, String dest )
  {
    sourceName = source;
    destName   = dest;
  }

  private boolean openFiles()  // return true if files open, else false
  {
    // open the source
    try
    {      
      source = new BufferedReader(new FileReader( sourceName ));
    }
    catch ( IOException iox )
    {
      System.out.println("Problem opening " + sourceName );
      return false;
    }
    // open the destination
    try
    {      
      dest = new PrintWriter( new BufferedWriter(new FileWriter( destName )) );
    }
    catch ( IOException iox )
    {
      System.out.println("Problem opening " + destName );
      return false;
    }
    return true;
  }

  private void copyFiles()   
  {
    try
    {      
      line = source.readLine();
      while ( line != null )
      {
        dest.println(line);
        line = source.readLine();
      }
    }
    catch ( IOException iox )
    {
      System.out.println("Problem reading or writing" );
    }
  }

  private void closeFiles()   
  {
    // close the source
    try
    {      
      source.close();
    }
    catch ( IOException iox )
    {
      System.out.println("Problem closing " + sourceName );
    }
    // close the destination
    dest.close();
  }

  public static void main ( String[] args ) 
  {
    if ( args.length == 3 && args[1].toUpperCase().equals("TO") )
    {
      CopyMaker cp = new CopyMaker( args[0], args[2] );
      if ( cp.openFiles() )
      {
        cp.copyFiles() ; 
        cp.closeFiles() ;
       }
    }
    else
      System.out.println("java CopyTextFile source to destination");
  }

}