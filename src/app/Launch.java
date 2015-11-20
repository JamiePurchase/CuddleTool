/*
 * Cuddle Tool
 * Jamie Purchase
 * 20/11/2015
 */
package app;

import files.FileSystem;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jamie
 */
public class Launch
{

    public static void main(String[] args)
    {
        // Debug
        System.console().writer().println("Hello 1");
        System.console().writer().print("Hello 2");
        
        // Check arguments
        if(args.length == 3)
        {
            String path = args[0];
            String ext = args[1];
            boolean sub = false;
            if(args[2].equals("true")) {sub = true;}
            
            /* Debug
            ArrayList<String> arguments = new ArrayList();
            arguments.add("Arguments");
            arguments.add(" path = '" + path + "'");
            arguments.add(" ext = '" + ext + "'");
            arguments.add(" sub = '" + sub + "'");
            */
            
            // Check directory
            if(new File(path).isDirectory())
            {
                //arguments.add(" is directory");
                fileCuddle(path, ext, sub);
            }
            //else {arguments.add(" is NOT directory");}
            //FileSystem.saveFile(arguments, "C:/Users/Jamie/Documents/My Projects/CuddleTool/result.txt");
        }
        
        //consoleInit();
        //String directory = "C:/Users/Jamie/Documents/My Projects/CuddleTool/";
        //fileCuddle(directory, "txt", false);
    }
    
    private static void consoleInit()
    {
        System.out.println("");
        System.out.println("     +----------------+");
        System.out.println("     | Cuddle Tool    |");
        System.out.println("     | Jamie Purchase |");
        System.out.println("     | 20/11/2015     |");
        System.out.println("     +----------------+");
        System.out.println("");
    }
    
    private static void fileCuddle(String directory, String extension, boolean subfolder)
    {
        // Get all files in directory
        ArrayList<File> file = FileSystem.getFolderContent(directory, extension, true, subfolder);
        
        // Process files
        System.out.println("Processing files");
        for(int f = 0; f < file.size(); f++)
        {
            fileCuddleFile(file.get(f).getAbsolutePath());
        }
        System.out.println("");
        System.out.println("Done");
    }
    
    private static void fileCuddleFile(String filePath)
    {
        // Debug
        System.out.println(" - " + filePath);
        
        // Load file data
        ArrayList<String> fileData = FileSystem.loadFile(filePath);
        
        // Create blank array
        ArrayList<String> newData = new ArrayList();
        
        // Iterate through data
        int newLines = 0;
        boolean add;
        for(int x = 0; x < fileData.size(); x++)
        {
            add = true;
            if(x > 0 && fileData.get(x).indexOf("{") >= 0)
            {
                add = false;
                newData.set(newLines - 1, newData.get(newLines - 1) + " {");
            }
            if(add)
            {
                newData.add(fileData.get(x));
                newLines += 1;
            }
        }
        
        // Save new file
        FileSystem.saveFile(newData, filePath);
    }

}