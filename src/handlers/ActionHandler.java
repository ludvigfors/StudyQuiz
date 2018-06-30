package handlers;

import objects.Category;
import objects.Question;

import java.util.ArrayList;

public interface ActionHandler
{

    public void addNewCategory(String categoryName);
    public void addNewQuestion(Question question, String selectedCategory);
    public void startQuizSelector();
    public void startCreate();
    public ArrayList<Category> getCategories();
    public void startQuizGame(final String selectedCategory);


}
