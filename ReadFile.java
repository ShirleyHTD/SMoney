package ui;

import model.AEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFile {
    //private static ArrayList<AEvent> events = new ArrayList<>();
    private static String inputFileName = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Data";
    private  static String outputFile = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Output";


    //EFFECTS: read the string in the input file then write it onto the output file
    public static void read() throws IOException {
        //String inputFileName = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Data";
        //String outputFile = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Output";
        PrintWriter writer = new PrintWriter(outputFile,"UTF-8");
        List<String> lines = Files.readAllLines(Paths.get(inputFileName));
        for (String line : lines) {
            writer.append(line);

        }
        writer.close(); //note -- if you miss this, the file will not be written at all.
    }




    //EFFECTS: get the string int the input file
    public static AEvent get() {
        //ArrayList<AEvent> events = new ArrayList<>();
        //fileName = inputFileName;
        AEvent x = null;
        //String fileName = "D:\\UBC_2019_term1\\CPSC 210\\BigProject\\project_u7a3b\\data\\Data";
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFileName));
            x =  addEvent(lines);
        } catch (IOException e) {
            System.out.println("File not found");
        }
//        finally {
//            return null;
//        }
        return x;
    }

    //EFFECTS: turn the strings into AEvents
    private static AEvent addEvent(List<String> lines) {
        //ArrayList<AEvent> events = new ArrayList<>();
        AEvent x = null;
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            int year = Integer.parseInt(partsOfLine.get(0));
            int month = Integer.parseInt(partsOfLine.get(1));
            int day = Integer.parseInt(partsOfLine.get(2));
            String category = partsOfLine.get(3);
            double amount = Double.parseDouble(partsOfLine.get(4));
            String description = partsOfLine.get(5);

            x =  new AEvent(year,month,day,category,amount,description);
            //events.add(new AEvent(year,month,day,category,amount,description));
        }
        return x;
    }

    //EFFECTS: split the string by '/'
    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("/");
        return new ArrayList<>(Arrays.asList(splits));
    }










/*
    public void appendToFile(String input) throws IOException {
        FileWriter fw = new FileWriter(fileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(input);

        out.close();
    }
*/
}

















//System.out.print("Date: " + partsOfLine.get(0) + "." + partsOfLine.get(1) + "." + partsOfLine.get(2));
//System.out.print("Amount: " + partsOfLine.get(3));
//System.out.print("Distribution: " + partsOfLine.get(4));