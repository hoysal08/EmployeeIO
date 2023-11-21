import filehandlers.MyFileHandler;
import model.Employee;
import model.MyCollection;

public class ThreadHelperReader extends Thread {

    String inputFilePath;
    MyCollection myCollection;
    MyFileHandler handler;

    ThreadHelperReader(String inputFilePath, MyCollection myCollection, MyFileHandler handler) {
        this.inputFilePath = inputFilePath;
        this.myCollection = myCollection;
        this.handler = handler;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Employee employee = (Employee) handler.read();
                myCollection.addEmployee(employee);
            } catch (Exception e) {
                System.out.println("Error reading File");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " : reader");

        }
    }
}