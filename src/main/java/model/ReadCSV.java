package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV 
{
    public static void main(String[] args) 
    {
        String path = "data/comics.csv";
        String line;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while( (line = br.readLine()) != null )
            {
                System.out.println(line);
            }
        }
        
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
