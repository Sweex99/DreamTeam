package applicationfolder.utils;

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
import java.util.logging.Logger;

public class DataBaseDriver {
    private Document document;
    private NodeList personsList;
    private int id;
    private Node user;
    private static final String XML_NAME = "database/users.xml";
    private Logger logger = Logger.getLogger("Logger");

    public DataBaseDriver() {
        try {
            XMLInit();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(XML_NAME);

            Node persons = document.getDocumentElement();
            personsList = persons.getChildNodes();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNickname() {
        return personsList.item(id).getChildNodes().item(0).getTextContent();
    }

    public String getTestings() {
        return personsList.item(id).getChildNodes().item(3).getTextContent();
    }

    public String getPercent() {
        return personsList.item(id).getChildNodes().item(4).getTextContent();
    }
    
    public String getNumber() {
        return personsList.item(id).getChildNodes().item(3).getTextContent();
    }

    public void editNickname(String nick) {
        personsList.item(id).getChildNodes().item(0).setTextContent(nick);
        updateXmlDocument();
    }

    public void editPassword(String pass) {
        String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
        personsList.item(id).getChildNodes().item(2).setTextContent(hashed);
        updateXmlDocument();
    }

    public boolean authorization(String login, String password) {
        if (!searchPerson(login)) {
            return false;
        }
        Node passNode = user.getChildNodes().item(2);
        id = Integer.parseInt(user.getAttributes().item(0).getTextContent());

        String passwordTextContent = passNode.getTextContent();

        return BCrypt.checkpw(password, passwordTextContent);
    }

    public boolean registration(String nicknameValue, String loginValue, String passwordValue) {
        if (searchPerson(loginValue)) {
            return false;
        } else {
            Node persons = document.getDocumentElement();

            Element person = document.createElement("person");
            person.setAttribute("id", Integer.toString(personsList.getLength()));

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

            updateXmlDocument();
            return true;
        }
    }

    public boolean searchPerson(String request) {
        try {
            String expression = "persons/person[login='" + request + "']";
            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();
            XPathExpression pathExpression = xpath.compile(expression);
            Node node = (Node) pathExpression.evaluate(document, XPathConstants.NODE);
            user = node;
            return node != null;
        } catch (DOMException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStatistic(int result) {
        int percent = Integer.parseInt(getPercent());
        int number = Integer.parseInt(getNumber());
        int arithmeticMean = (percent * number + result * 10) / (number + 1);
        number++;
        personsList.item(id).getChildNodes().item(3).setTextContent(Integer.toString(number));
        personsList.item(id).getChildNodes().item(4).setTextContent(Integer.toString(arithmeticMean));

        updateXmlDocument();
    }

    private void updateXmlDocument() {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(XML_NAME));
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void XMLInit() {
        try {
            File folder = new File("database");
            File xmlFile = new File(XML_NAME);
            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!xmlFile.exists()) {
                boolean isCreate = xmlFile.createNewFile();
                if (!isCreate) {
                    logger.info("Error! File is not created!");
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(XML_NAME))) {
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