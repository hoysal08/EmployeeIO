package filehandlers;

import com.opencsv.*;
import filehandlers.MyFileHandler;
import model.Employee;

import java.io.*;
import java.text.*;
import java.util.*;


public class CSVFileHandler implements MyFileHandler<Employee> {

    int readItemsCount = 0;
    final SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yy");
    String inputFilePath;
    String outputFilePath;

    public CSVFileHandler(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public Employee read() throws Exception {

        FileReader fileReader = new FileReader(inputFilePath);
        CSVReader csvReader = new CSVReader(fileReader);

        List<String[]> allData = csvReader.readAll();

        int index = 0;
        for (String[] item : allData) {
            if (index == readItemsCount) {
                Date employeeDate = dateFormat.parse(item[2]);
                fileReader.close();
                readItemsCount++;

                return new Employee(item[0], item[1], employeeDate, Double.parseDouble(item[3]));
            }
            index++;
        }
        fileReader.close();
        return null;
    }

    public void write(Employee employee) throws Exception {
        String[] dataToBeWritten = {employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth().toString(), Double.toString(employee.getExperience())};

        FileWriter outputFile = new FileWriter(outputFilePath, true);
        CSVWriter writer = new CSVWriter(outputFile);

        writer.writeNext(dataToBeWritten);
        writer.close();
    }
}
