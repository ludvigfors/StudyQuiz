package mainprogram;

import objects.Category;
import objects.Question;

import java.util.ArrayList;

public interface ActionHandler
{

   // public void switchPanels(CustomPanels newPanel);
    public void addNewCategory(String categoryName);
    public void addNewQuestion(Question question, String selectedCategory);
    public void startQuiz();
    public void startCreate();
    public ArrayList<Category> getCategories();


}
