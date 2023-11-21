import filehandlers.MyFileHandler;
import model.Employee;
import model.MyCollection;

public class ThreadHelperWriter extends Thread {

    String outputFilePath;
    MyCollection myCollection;
    MyFileHandler handler;

    ThreadHelperWriter(MyCollection myCollection, String outputFilePath, MyFileHandler handler) {
        this.outputFilePath = outputFilePath;
        this.myCollection = myCollection;
        this.handler = handler;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Employee employeeToBeWritten = myCollection.getEmployee();
                handler.write(employeeToBeWritten);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":  writer");

        }
    }
}