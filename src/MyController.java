import filehandlers.CSVFileHandler;
import filehandlers.XMLFileHandler;
import model.MyCollection;

public class MyController {

    public static void main(String args[]) throws InterruptedException {

        MyCollection collection = new MyCollection();

        final String inputPathCSV = "/Users/soorajhoysalka/IdeaProjects/EmployeeIO/src/data/employee.csv";
        final String outputPathCSV = "/Users/soorajhoysalka/IdeaProjects/EmployeeIO/src/data/employee_op.csv";

        final String inputPathXML = "/Users/soorajhoysalka/IdeaProjects/EmployeeIO/src/data/employee.xml";
        final String outputPathXML = "/Users/soorajhoysalka/IdeaProjects/EmployeeIO/src/data/employee_op.xml";

        Thread csvReader = new ThreadHelperReader(inputPathCSV, collection, new CSVFileHandler(inputPathCSV, outputPathCSV));
        Thread xmlReader = new ThreadHelperReader(inputPathXML, collection, new XMLFileHandler(inputPathXML, outputPathXML));

        csvReader.start();
        xmlReader.start();

        csvReader.join();
        xmlReader.join();


        Thread csvWriter = new ThreadHelperWriter(collection, outputPathCSV, new CSVFileHandler(inputPathCSV, outputPathCSV));
        Thread xmlWriter = new ThreadHelperWriter(collection, outputPathXML, new XMLFileHandler(inputPathXML, outputPathXML));

        csvWriter.start();
        xmlWriter.start();

        csvWriter.join();
        xmlWriter.join();

        System.out.println("Write Count after all operations : " + collection.getWriteCounter());
        System.out.println("Read Count after all operations : " + collection.getReadCounter());

    }
}
