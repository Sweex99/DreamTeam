package applicationfolder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DataBaseController {
    private NodeList personsList;
    private Node person;
    private Node nickname;
    private Node login;
    private Node password;
    private static Document document;

    DataBaseController() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("database/users.xml");

            Node persons = document.getDocumentElement();
            personsList = persons.getChildNodes();
            /*person = personsList.item(1);

            NodeList personList = person.getChildNodes();
            nickname = personList.item(1);
            login = personList.item(3);
            password = personList.item(5);*/
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node getNickname () {
        return nickname;
    }

    public void setNickname(Node nickname) {
        this.nickname = nickname;
    }

    public Node getLogin () {
        return login;
    }

    public void setLogin(Node login) {
        this.login = login;
    }

    public Node getPassword () {
        return password;
    }

    public void setPassword(Node password) {
        this.password = password;
    }

    /*private void setNickname (String nicknameTextContent) {
        this.nickname.setTextContent(nicknameTextContent);
        updateXMLDocument();
    }*/

    public boolean authorization(String login, String password) {
        searchPerson(login);
        setNickname(person.getChildNodes().item(1));
        setLogin(person.getChildNodes().item(3));
        setPassword(person.getChildNodes().item(5));
        return login.equals(getLogin().getTextContent()) && password.equals(getPassword().getTextContent());
    }

    public void registration(String nicknameValue, String loginValue, String passwordValue) {
        if(searchPerson(loginValue)) {
            WindowMessage.winAlert("Такий логін вже зайнятий");
            return;
        }

        Node persons = document.getDocumentElement();

        Element person = document.createElement("person");
        person.setAttribute("id", personsList.getLength()+"");

        Element nickname = document.createElement("nickname");
        nickname.setTextContent(nicknameValue);

        Element login = document.createElement("nickname");
        login.setTextContent(loginValue);

        Element password = document.createElement("nickname");
        password.setTextContent(passwordValue);

        person.appendChild(nickname);
        person.appendChild(login);
        person.appendChild(password);
        persons.appendChild(person);

        updateXMLDocument();
    }

    private boolean searchPerson(String request) {
        Node currentItem;
        for(int i = 1; i < personsList.getLength(); i+=2) {
            currentItem = personsList.item(i);
            if(currentItem.getChildNodes().item(3).getTextContent().equals(request)) {
                this.person = currentItem;
                return true;
            }
        }
        WindowMessage.winAlert("Не вдалося знайти особу!");
        return false;
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

//    public void test() {
//        try {
//            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document document = documentBuilder.parse("database/users.xml");
//
//            Node root = document.getDocumentElement();
//
//            System.out.println("List of books:");
//            System.out.println();
//            NodeList persons = root.getChildNodes();
//            Node person = persons.item(1);
//            NodeList personList = person.getChildNodes();
//            Node n = personList.item(1);
//            n.setTextContent("Dick");
//            try {
//                Transformer tr = TransformerFactory.newInstance().newTransformer();
//                DOMSource source = new DOMSource(document);
//                StreamResult result = new StreamResult(new FileOutputStream("database/users.xml"));
//                tr.transform(source, result);
//            } catch (TransformerException | IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            String s = n.getTextContent();
//            System.out.print(s);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
