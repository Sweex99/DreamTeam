package applicationfolder;

import org.mindrot.jbcrypt.BCrypt;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.*;

public class DataBaseDriver {
    private static Document document;
    private static NodeList personsList;
    private static Node person;
    private static Node nickname;
    private static int id;

    DataBaseDriver() {
        try {
            XMLInit();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("database/users.xml");

            Node persons = document.getDocumentElement();
            personsList = persons.getChildNodes();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNickname() {
        return nickname.getTextContent();
    }

    public String getTestings() {
        return personsList.item(id).getChildNodes().item(3).getTextContent();
    }

    public String getPercent() {
        return personsList.item(id).getChildNodes().item(4).getTextContent();
    }

    public void editNickname(String nick) {
        personsList.item(id).getChildNodes().item(0).setTextContent(nick);
        nickname.setTextContent(nick);
        updateXMLDocument();
    }

    public void editPassword(String pass) {
        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
        personsList.item(id).getChildNodes().item(2).setTextContent(hashed);
        updateXMLDocument();
    }

    public boolean authorization(String login, String password) {
        if (!searchPerson(login)) {
            return false;
        }
        this.nickname = person.getChildNodes().item(0);
        Node passNode = person.getChildNodes().item(2);
        this.id = Integer.parseInt(person.getAttributes().item(0).getTextContent());

        String passwordTextContent = passNode.getTextContent();

        return BCrypt.checkpw(password, passwordTextContent);
    }

    public void registration(String nicknameValue, String loginValue, String passwordValue) {
        if (searchPerson(loginValue)) {
            return;
        }
        Node persons = document.getDocumentElement();

        Element person = document.createElement("person");
        person.setAttribute("id", personsList.getLength() + "");

        Element nickname = document.createElement("nickname");
        nickname.setTextContent(nicknameValue);

        Element login = document.createElement("login");
        login.setTextContent(loginValue);

        Element password = document.createElement("password");
        String hash = BCrypt.hashpw(passwordValue, BCrypt.gensalt());
        password.setTextContent(hash);

        Element testings = document.createElement("testings");
        testings.setTextContent("0");

        Element percent = document.createElement("percent");
        percent.setTextContent("0");

        person.appendChild(nickname);
        person.appendChild(login);
        person.appendChild(password);
        person.appendChild(testings);
        person.appendChild(percent);
        persons.appendChild(person);

        updateXMLDocument();
    }

    public boolean searchPerson(String request) {
        try {
            String expression = "persons/person[login='" + request + "']";
            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();
            XPathExpression pathExpression = xpath.compile(expression);
            Node node = (Node) pathExpression.evaluate(document, XPathConstants.NODE);
            this.person = node;
            return node != null;
        } catch (DOMException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatistic(int result) {
        int percent = Integer.parseInt(personsList.item(id).getChildNodes().item(4).getTextContent());
        int number = Integer.parseInt(personsList.item(id).getChildNodes().item(3).getTextContent());
        int arithmeticMean = (percent * number + result * 10) / (number + 1);
        number++;
        personsList.item(id).getChildNodes().item(3).setTextContent(number + "");
        personsList.item(id).getChildNodes().item(4).setTextContent(arithmeticMean + "");

        updateXMLDocument();
    }

    private void updateXMLDocument() {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream("database/users.xml"));
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void XMLInit() {
        try {
            File folder = new File("database");
            File xmlFile = new File("database/users.xml");
            if (!folder.exists() || !xmlFile.exists()) {
                folder.mkdir();
                xmlFile.createNewFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("database/users.xml"))) {
                    bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><persons></persons>");
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}