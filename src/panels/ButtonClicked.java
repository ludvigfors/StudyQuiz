package panels;

import mainprogram.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClicked implements ActionListener
{


    public ButtonClicked() {}

    @Override public void actionPerformed(final ActionEvent e) {

        switch (e.getActionCommand()){
	    case Constants.BUTTON_CREATE:
	        saveNewQuestion();
	        break;
	}
    }

    private void saveNewQuestion() {

    }
}
