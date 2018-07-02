package backend_logic;

import objects.Category;
import objects.Question;
import objects.RootXMLClass;
import objects.StudyPanel;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Here, the main logic of the program opperates.
 *
 * It takes in the xml file and knows which panel should be in the frame
 * and what data it will be displaying.
 *
 *
 */
public class StudyQuiz
{
    // General data
    /**
     * The XML file that stores the data
     */
    public static final String QUESTIONS_XML = "questions.xml";
    /**
     * Message appears if no questions wheer found for a selected category.
     */
    public static final String MESSAGE_NO_QUESTIONS = "No questions for this category";
    /**
     * Index for the first question in the quiz.
     */
    public static final int QUIZ_MIN = 0;
    /**
     * Index for the last question in the quiz.
     */
    public static final int QUIZ_MAX = 10;


    private Marshaller marshaller = null;
    private Unmarshaller unmarshaller = null;

    private StudyPanel currentPanel;
    private List<QuizListener> quizListeners;
    private RootXMLClass rootXMLClass = null;

    //Quiz Data
    private int currentQuestionIndex = 0; // MAGISK KONSTANT
    private int playerScore = 0;
    private List<Question> questions = new ArrayList<>(); // The questions for one game
    private String lastSubmittedAnswer = "";

    public StudyQuiz(){
	this.quizListeners = new ArrayList<>();
	this.currentPanel = StudyPanel.CREATION_PANEL;
	createJAXBObjects();
	readXMLFile();
	notifyListeners();
    }

    private void createJAXBObjects()  {
	//initilize JAXB for reading and writing to xml
	try {
	    JAXBContext context = JAXBContext.newInstance(RootXMLClass.class);

	    //Makes it so you can write to xml file
	    marshaller = context.createMarshaller();
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // HMMMM NOPE

	    // Gives assignend xmlElement a unique id
	    marshaller.setAdapter(new IDAdapter());
	    // Makes it so you can read xml file
	    unmarshaller = context.createUnmarshaller();
	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }


    private void readXMLFile()  {
	try {
	    rootXMLClass = (RootXMLClass) unmarshaller.unmarshal(new FileReader(QUESTIONS_XML)); // Behövs new ?
	} catch (JAXBException | FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private void writeToXMLFile() {
	try {
	    //Write to System.out
	    marshaller.marshal(rootXMLClass, System.out);

	    //Write to xml file
	    marshaller.marshal(rootXMLClass, new File(QUESTIONS_XML)); // måste det vara new?

	} catch (JAXBException e) {
	    e.printStackTrace();
	}
    }


    //Creation panel methods
    public void addNewCategory(String categoryName) {
	if(!categoryName.isEmpty() && categoryExists(categoryName)){
	    rootXMLClass.addCategory(new Category(categoryName));
	    writeToXMLFile();

	}
    }

    public void addNewQuestion(Question question, String selectedCategory) {
	rootXMLClass.addQuestionToCategory(question, selectedCategory);
	writeToXMLFile();
    }

    private boolean categoryExists(String wantedcategoryName) {
	for (Category category : rootXMLClass.getCategories()) {
	    if(category.getName().equals(wantedcategoryName)){
		return false;
	    }
	}
	return true;
    }




    //Quiz panel methods
    public void startQuizGame(final String selectedCategory) {
	questions = generateTenQuestions(selectedCategory);
	if(questions.isEmpty()){
	    JOptionPane.showMessageDialog(null, MESSAGE_NO_QUESTIONS);
	} else {
	    showQuestionDisplayPanel();
	}

    }

    private List<Question> generateTenQuestions(final String selectedCategory) {
	List<Question> questionList = rootXMLClass.getCategoryQuestions(selectedCategory);
	Collections.shuffle(questionList);
	if(questionList.size() > QUIZ_MAX){
	    return questionList.subList(QUIZ_MIN, QUIZ_MAX);
	} else{
	    return questionList;
	}
    }

    public void checkAnswer(final String submitedAnswer) {
	lastSubmittedAnswer = submitedAnswer;
	if(getCurrentQuestion().getAcceptedAnswers().contains(submitedAnswer)){
	    playerScore++;
	    goToNextQuestion();
	} else{
	    showAnswerCheckPanel();

	}}

    public void goToNextQuestion() {
	currentQuestionIndex++;
	if(isQuizDone()){
	    quizDone();
	} else {
	    showQuestionDisplayPanel();
	}
    }

    public void quizDone() {
	writeToXMLFile();
	currentPanel = StudyPanel.RESULT_PANEL;
	notifyListeners();
	currentQuestionIndex = 0;
	playerScore = 0;
    }


    //Manuver methods

    private void notifyListeners(){
	for(QuizListener listener : quizListeners){
	    listener.quizChanged();
	}
    }
    public void showCreationPanel() {
	currentPanel = StudyPanel.CREATION_PANEL;
	notifyListeners();
    }

    public void showQuizSelectorPanel(){
	currentPanel = StudyPanel.QUIZ_SELECT_PANEL;
	notifyListeners();
    }

    private void showQuestionDisplayPanel() {
	currentPanel = StudyPanel.DISPLAY_PANEL;
	notifyListeners();
    }

    private void showAnswerCheckPanel() {
	currentPanel = StudyPanel.ANSWER_CHECK_PANEL;
	notifyListeners();
    }

    public String generateResult() {
	String result = playerScore + "/" + questions.size();
	return result;

    }
    //Add, get, set, is

    public boolean isQuizDone(){
	return (currentQuestionIndex + 1) > questions.size();
    }

    public StudyPanel getCurrentPanel() {
	return currentPanel;
    }

    public RootXMLClass getRootXMLClass() {
	return rootXMLClass;
    }

    public int getCurrentQuestionIndex() {
	return currentQuestionIndex;
    }

    public Question getCurrentQuestion(){
	return questions.get(currentQuestionIndex);
    }

    public String getLastSubmittedAnswer() {
	return lastSubmittedAnswer;
    }



    public void addQuizListener(QuizListener quizListener){
	quizListeners.add(quizListener);
	notifyListeners();
    }

    public void addGoodQuestionAndTakeNextQuestion(final String playerAnswer) {
	playerScore++;
	addPlayerAnswer(playerAnswer);
	goToNextQuestion();
    }

    private void addPlayerAnswer(final String playerAnswer) {
	for (Category category : rootXMLClass.getCategories()) {
	    for(Question question : category.getQuestionList()){
		if(question.getId().equals(getCurrentQuestion().getId())){
		    question.addGoodAnswer(playerAnswer);
		    return;
		}
	    }
	}
    }
}
