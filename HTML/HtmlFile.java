package HTML;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Optional;

import Exceptions.FileExtensionException;
import Exceptions.FileNameException;
import HTML.Components.Component;
import HTML.Components.BaseComponents.Body;
import HTML.Components.BaseComponents.Head;
import HTML.Components.BaseComponents.Html;

/**
 * Represents an HTML file in memory.
 */
public class HtmlFile{
    private Html htmlComponent;
    private Component body;

    public HtmlFile(){
        htmlComponent = new Html();
        body = new Body();
        htmlComponent.addChildren(new Head(), body);
    }

    public Component getBodyComponent(){
        return body;
    }

    /**
     * Writes the HTML code contained within to a file. 
     * @param fileName The name of the output file
     */
    public void save(String fileName){
        if (!ComponentChecker.clearToSave()){
            System.out.println("Saving cancelled due to free components.");
            return;
        }

        Optional<String> saveName = parseFileName(fileName);

        if (saveName.isEmpty()){
            return;
        }


        try{
            writeFile(saveName.get());
        }
        catch (Exception e){
            System.err.println("Error saving file");

            e.printStackTrace();
        }
    }

    private void writeFile(String fileName) throws Exception{
        DataOutputStream file;

        file = new DataOutputStream(new FileOutputStream(fileName));

        char[] data = htmlComponent.write().toCharArray();

        //Write
        for (char character : data){
            file.write(character);
        }

        file.close();
    }

    private Optional<String> parseFileName(String fileName){
        String[] fileNameParts = fileName.split("\\.");

        try{
            if (fileName.isBlank() || fileName.isEmpty()){
                throw new FileNameException(fileName);
            }
            else if (fileNameParts.length > 2){
                throw new FileExtensionException(fileName);
            }
            else if (fileNameParts.length == 2 && !fileNameParts[1].equals("html")){
                throw new FileExtensionException(fileName);
            }
        }
        catch(Exception e){
            System.out.println("Error saving file");
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(fileNameParts[0]+".html");
    }
}