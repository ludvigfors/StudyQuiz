package backend_logic;

/**
 * The swedish language constants.
 */
public final class LanguageConstants
{	// Creation
    /**
        * Text for the category combo box.
        */
       public static final String COURCE_LABEL_TEXT = "Vilken kategori?";
       /**
        * Text for the label for adding a new category to the combo box.
        */
       public static final String NEW_CATEGORY_LABEL_TEXT = "Ny kategori";
       /**
        * Text for the question textfield.
        */
       public static final String QUESTION_LABEL_TEXT = "Skriv in frågan";
       /**
        * Text for the answer textfield.
        */
       public static final String ANSWER_LABEL_TEXT = "Skriv in svaret";

    /**
        * Text on the button that adds a new categoryLabel.
        */
       public static final String BUTTON_ADD_CATEGORY_TEXT = "Lägg till";
       /**
        * Text on the button that creates a new question.
        */
       public static final String BUTTON_CREATE_TEXT = "Spara frågan";
       /**
        * Text on the button that starts a quiz.
        */
       public static final String BUTTON_QUIZ_TEXT = "Starta quizet!!!!";

    /**
     * Message appears if all fields are not filled in when user tries to create a question.
     */
    public static final String MESSAGE_FILL_ALL_FIELDS = "Fyll i alla fält först";

    /**
     * Message appears if no category name was entered and the add category button was clicked.
     */
    public static final String MEESAGE_NO_CATEGORY_INPUTED = "Ingen kategori skriven";

       // Question Display
    /**
         * The text on the end button.
         */
        public static final String END_BUTTON_TEXT = "Avsluta";
        /**
         * The text on the submit button.
         */
        public static final String SUBMIT_BUTTON_TEXT = "Svara!";
        /**
         * The message text for if no answer was inputed when the submit button is clicked.
         */

        public static final String MESSAGE_NO_ANSWER_INPUTED = "Fyll i ett svar";

        // Selector
    /**
        * The text for the label for the combobox.
        */
       public static final String CATEGORY_CHOOSER_LABEL_TEXT = "Vilken kurs vill du förhöra dig på?";
       /**
        * The text on the back button.
        */
       public static final String BACK_BUTTON_TEXT = "Tillbaka";

    /**
     * Message shows up if no category is selected on start of a quiz.
     */
    public static final String MESSAGE_CHOOSE_A_CATEGORY = "Välj en kurs att spela på!";

       // Result

    /**
     * The text on the heading i result panel.
     */
    	public static final String CONGRATULATIONS_LABEL_TEXT = "Grattis!!!";
    /**
     * The text on the left of the score.
     */
       public static final String RESULT_LABEL_TEXT = "Du fick: ";
    /**
     * The text on the button that takes the user to the start screen.
     */
       public static final String START_SCREEN_LABEL_TEXT = "Gå till startsidan";
    /**
     * The text on the button that makes the user play again.
     */
       public static final String PLAY_AGAIN_LABEL_TEXT = "Spela igen?";

    /**
     * The text on the go button that starts the quiz.
     */
    public static final String GO_BUTTON_TEXT = "GO!!!";

    // Answer

    /**
     * The text on the yes button.
     */
    public static final String YES_BUUTON = "JA";
    /**
     * The text on the no button.
     */
       public static final String NO_BUTTON = "NEJ";

    /**
     * The text on the correct answer label.
     */
    public static final String CORRECT_ANSWER_LABEL = "Rätt svar: ";
    /**
     * The text on the player answer label.
     */
        public static final String PLAYER_ANSWER_LABEL = "Ditt svar: ";
    /**
     * The text on the was it correct label.
     */
        public static final String WAS_IT_CORRECT_LABEL = "Var det rätt? ";

    private LanguageConstants() {}
}
