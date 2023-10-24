package Utils;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class FileUtils {
    public static void dumpString(String text, String fileName){
        try{
            DataOutputStream file;

            file = new DataOutputStream(new FileOutputStream(fileName));

            char[] data = text.toCharArray();

            //Write
            for (char character : data){
                file.write(character);
            }

            file.close();
        }
        catch(Exception e){
            System.err.println("ERROR: Dumping file");
            e.printStackTrace();
        }
    }
}
