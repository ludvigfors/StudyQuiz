package mainprogram;


import objects.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import panels.ButtonClicked;
import panels.CreationPanel;
import javax.xml.parsers.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame
{
    private JPanel panel;
    private BorderLayout layout;
    private CreationPanel questionCreationPanel;


    public MainFrame() {
	super("Study Quiz");
	createPanel();
	createFrame();
    }

    private void createPanel() {
	questionCreationPanel = new CreationPanel();
	setLayout(new BorderLayout());
	questionCreationPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

	// Quiz button
	JButton quizStartButton = new JButton();
	quizStartButton.addActionListener(new ButtonClicked());
	quizStartButton.setText("Start a quiz!!!");

	add(questionCreationPanel, BorderLayout.NORTH);
	add(quizStartButton,BorderLayout.SOUTH);
    }

    private void readXMLFile() {
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	String catName = "TDDD78";
	try{
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse("questions.xml");
	    NodeList categoryList = doc.getElementsByTagName("category");
	    for(int i = 0; i<categoryList.getLength();i++){
	        Element category = (Element) categoryList.item(i);
	        String categoryName = category.getAttribute("name");
	        if(categoryName.equals(catName)){
	           int num = 0;
		}
	    }


	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	} catch (SAXException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void createFrame() {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	pack();
	//setSize(400,200);
	setVisible(true);
	//setResizable(false);

    }

    private void onCreateButtonClicked(){
        String category = questionCreationPanel.getCategory();
	Question question = new Question(questionCreationPanel.getCategory(), questionCreationPanel.getQuestion(),
					 questionCreationPanel.getAnswer());

    }
}
