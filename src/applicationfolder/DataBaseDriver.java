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
    private NodeList personsList;
    private Node person;
    private Node nickname;
    private Node login;
    private Node password;
    public String userName;

    DataBaseDriver() {
        try {
            XMLExists();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("database/users.xml");

            Node persons = document.getDocumentElement();
            personsList = persons.getChildNodes();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getNickname() {
        return nickname;
    }

    public void setNickname(Node nickname) {
        this.nickname = nickname;
    }

    public Node getLogin() {
        return login;
    }

    public void setLogin(Node login) {
        this.login = login;
    }

    public Node getPassword() {
        return password;
    }

    public void setPassword(Node password) {
        this.password = password;
    }

    public boolean authorization(String login, String password) {
        if(!searchPerson(login)) {
            return false;
        }
        setNickname(person.getChildNodes().item(1));
        setLogin(person.getChildNodes().item(3));
        setPassword(person.getChildNodes().item(5));

        String passwordTextContent = getPassword().getTextContent();

        return BCrypt.checkpw(password, passwordTextContent);
    }

    public void registration(String nicknameValue, String loginValue, String passwordValue) {
        if (searchPerson(loginValue)) {
            WindowMessage.winAlert("Такий логін вже зайнятий");
            return;
        }

        Node persons = document.getDocumentElement();

        Element person = document.createElement("person");
        person.setAttribute("id", ((personsList.getLength() - 1) / 2) + "");

        Element nickname = document.createElement("nickname");
        nickname.setTextContent(nicknameValue);

        Element login = document.createElement("login");
        login.setTextContent(loginValue);

        Element password = document.createElement("password");
        String hash = BCrypt.hashpw(passwordValue, BCrypt.gensalt());
        password.setTextContent(hash);

        person.appendChild(nickname);
        person.appendChild(login);
        person.appendChild(password);
        persons.appendChild(person);

        updateXMLDocument();
    }

    private boolean searchPerson(String request) {
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

    private void XMLExists () {
        try {
            File folder = new File("database");
            File xmlFile = new File("database/users.xml");
            if (!folder.exists() && !xmlFile.exists()) {
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
