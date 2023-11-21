package filehandlers;

import filehandlers.MyFileHandler;
import model.Employee;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLFileHandler implements MyFileHandler<Employee> {

    int readItemsCount = 0;
    boolean writeXMLinit = false;
    SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yy");

    String inputFilePath;
    String outputFilePath;

    public XMLFileHandler(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public Employee read() throws Exception {

        File file = new File(inputFilePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("employee");

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);
            if (i == readItemsCount) {
                Element employeeElement = (Element) node;

                String firstName = employeeElement.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = employeeElement.getElementsByTagName("lastName").item(0).getTextContent();
                String employeeDateString = employeeElement.getElementsByTagName("dateOfBirth").item(0).getTextContent();
                String employeeExpereince = employeeElement.getElementsByTagName("experience").item(0).getTextContent();

                Date employeeDate = dateFormat.parse(employeeDateString);
                readItemsCount++;
                return new Employee(firstName, lastName, employeeDate, Double.parseDouble(employeeExpereince));
            }
        }
        return null;
    }

    public void write(Employee employee) throws Exception {

        File file = new File(outputFilePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        if(writeXMLinit){
            doc = db.parse(file);
        }

        Element root;
        Element employeeElement = doc.createElement("employee");

        if (writeXMLinit) {
            //already root exists
            root = doc.getDocumentElement();
        } else {
            //root needs to be created
            Element rootElement = doc.createElement("employees");
            doc.appendChild(rootElement);
            root = rootElement;
            writeXMLinit = true;
        }

        Element firstNameElement = doc.createElement("firstName");
        firstNameElement.appendChild(doc.createTextNode(employee.getFirstName()));
        employeeElement.appendChild(firstNameElement);


        Element lastNameElement = doc.createElement("lastName");
        lastNameElement.appendChild(doc.createTextNode(employee.getLastName()));
        employeeElement.appendChild(lastNameElement);


        Element dateOfBirthElement = doc.createElement("dateOfBirth");
        dateOfBirthElement.appendChild(doc.createTextNode(dateFormat.format(employee.getDateOfBirth())));
        employeeElement.appendChild(dateOfBirthElement);


        Element experienceElement = doc.createElement("experience");
        experienceElement.appendChild(doc.createTextNode(String.valueOf(employee.getExperience())));
        employeeElement.appendChild(experienceElement);


        root.appendChild(employeeElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

}
