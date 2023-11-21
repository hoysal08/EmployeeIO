package model;

import exceptions.CollectionOverFlow;
import exceptions.CollectionUnderFlow;
import model.Employee;

public class MyCollection {

    public final static int SIZE = 200;

    private int writeCounter = 0;
    private int readCounter = 0;

    public int getWriteCounter() {
        return writeCounter;
    }

    public void setWriteCounter(int writeCounter) {
        this.writeCounter = writeCounter;
    }

    public int getReadCounter() {
        return readCounter;
    }

    public void setReadCounter(int readCounter) {
        this.readCounter = readCounter;
    }


    Employee array[] = new Employee[SIZE];

    public synchronized void  addEmployee(Employee employee) throws CollectionOverFlow {

        if (writeCounter > SIZE) {
            throw new CollectionOverFlow("employee full");
        } else {
            array[writeCounter] = employee;
            writeCounter++;
        }
    }

    public synchronized Employee getEmployee() throws CollectionUnderFlow {
        if (readCounter > SIZE) {
            throw new CollectionUnderFlow("no employees");
        }
        Employee employee = array[readCounter];
        readCounter++;
        return employee;
    }
}