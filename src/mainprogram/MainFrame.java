package mainprogram;


import net.miginfocom.swing.MigLayout;
import objects.Category;
import objects.Question;
import objects.RootXMLFile;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import panels.ButtonClicked;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame implements ActionListener
{
    public static final String QUESTIONS_XML = "questions.xml";
    private final JAXBContext context;
    private JPanel createPanel;
    private String[] dummyCred = {"TATA65","TDDD78"};
    private Border TEXT_FIELD_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private JTextField categoryField;
    private JTextField questionField;
    private JTextField answerField;
    private JLabel category;
    private JLabel newCategory;
    private JLabel questName;
    private JLabel answerName;
    private JButton categoryButton;
    private JButton createButton;
    private JComboBox<String> categoryComboBox;
    private RootXMLFile rootXMLFile;


    public MainFrame() throws JAXBException {
	super("Study Quiz");

	// create JAXB context
	context = JAXBContext.newInstance(RootXMLFile.class);
	categoryComboBox = new JComboBox<>();
	readXMLFile();
	updateComboBox();
	createContent();
	createFrame();
    }

    private void updateComboBox() {
        if(rootXMLFile != null) {
	    categoryComboBox.removeAllItems();
	    for (Category category : rootXMLFile.getCategories()) {
		categoryComboBox.addItem(category.getName());
	    }

	}
    }


    private void createCategoryComboBox(final NodeList categoryNodeList) {
	List<String> categoryList = new ArrayList<>();
	for (int i=0;i<categoryNodeList.getLength();i++){
	    Element category = (Element) categoryNodeList.item(i);
	    categoryList.add(category.getAttribute("name"));
	}
	String[] categoryArray = categoryList.toArray(new String[categoryList.size()]);
	categoryComboBox = new JComboBox<>(categoryArray);
	categoryComboBox.setSelectedIndex(0);
    }

    private void createContent() {
	setLayout(new BorderLayout());
	createPanel = new JPanel(new MigLayout());
	createPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
	fillPanelWithContent();

	// Quiz button
	JButton quizStartButton = new JButton();
	quizStartButton.addActionListener(new ButtonClicked());
	quizStartButton.setText(Constants.BUTTON_ADD);

	add(createPanel, BorderLayout.NORTH);
	add(quizStartButton,BorderLayout.SOUTH);
    }

    private void fillPanelWithContent() {

	createButtons();
	createLables();
	createTextFields();
	addComponentsToMainPanel();

    }

    private void addComponentsToMainPanel() {

	createPanel.add(category);
	createPanel.add(categoryComboBox, "split2");
	createPanel.add(newCategory);
	createPanel.add(categoryField);
	createPanel.add(categoryButton, "wrap");
	createPanel.add(questName);
	createPanel.add(questionField, "wrap");
	createPanel.add(answerName);
	createPanel.add(answerField);
	createPanel.add(createButton, "wrap");
    }


    private void createTextFields() {
	categoryField = new JTextField(10);
	questionField = new JTextField(10);
	answerField = new JTextField(10);
	categoryField.setBorder(TEXT_FIELD_BORDER);
	questionField.setBorder(TEXT_FIELD_BORDER);
	answerField.setBorder(TEXT_FIELD_BORDER);
    }

    private void createFrame() {
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	pack();
	//setSize(400,200);
	setVisible(true);
	//setResizable(false);

    }

    private void createLables() {
	category = new JLabel("What cource?");
	newCategory = new JLabel("New Category");
	questName = new JLabel("The question");
	answerName = new JLabel("The answer");
    }

    private void createButtons() {
	categoryButton = new JButton(Constants.BUTTON_ADD);
	categoryButton.addActionListener(this);
	createButton = new JButton(Constants.BUTTON_CREATE);
	createButton.addActionListener(this);
    }


    @Override public void actionPerformed(final ActionEvent e) {

	if(e.getSource().equals(createButton)){
	    addNewQuestion();
	} else if(e.getSource().equals(categoryButton)){
	    addNewCategory();
	}

    }

    private void addNewCategory() {
	String categoryName = categoryField.getText();
	categoryField.setText("");
	if(!categoryName.isEmpty() && categoryExists(categoryName)){
	    Category newCategory = new Category(categoryName);
	    rootXMLFile.addCategory(newCategory);
	    writeToXMLFile();
	    readXMLFile();
	    updateComboBox();
	}
    }

    private boolean categoryExists(String wantedcategoryName) {
	for (Category category : rootXMLFile.getCategories()) {
	    if(category.getName().equals(wantedcategoryName)){
	        return false;
	    }
	}
	return true;
    }

    private void addNewQuestion() {
	String query = questionField.getText();
	String answer = answerField.getText();
	Question question = new Question(query, answer);
	if(categoryComboBox.getSelectedItem() != null) {
	    rootXMLFile.addQuestionToCategory(question, categoryComboBox.getSelectedItem().toString());
	} else {
	    JOptionPane.showMessageDialog(null, "No category found");
	}
    	writeToXMLFile();
    }

    private void writeToXMLFile() {
	try {
	    // instaniate marshaller
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);

	    //Write to System.out
	    m.marshal(rootXMLFile,System.out);

	    //Write to xml file
	    m.marshal(rootXMLFile, new File(QUESTIONS_XML)); // måste det vara new?

	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }


    private void readXMLFile()  {
	try {
	    // instansiate unmarshaller
	    Unmarshaller um = context.createUnmarshaller();
	    rootXMLFile = (RootXMLFile) um.unmarshal(new FileReader(QUESTIONS_XML)); // Behövs new ?

	} catch (JAXBException | FileNotFoundException e) {
	    e.printStackTrace();
	}
    }


}
