package testcases.CF012_USESENSITIVECLS__CWE492;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class CF012_USESENSITIVECLS__CWE492_SensitiveBad_4 extends Applet {

    // private member variables for applet components
    private Label enterUrlLabel;
    private TextField enterUrlTextField;
    private Button submitButton;

// init method that adds components to applet

    // and creates button listener object
    public void init() {
        setLayout(new FlowLayout());
        enterUrlLabel = new Label("Enter URL: ");
        enterUrlTextField = new TextField("", 20);
        submitButton = new Button("Submit");
        add(enterUrlLabel);
        add(enterUrlTextField);
        add(submitButton);
        ActionListener submitButtonListener = new SubmitButtonListener();
        submitButton.addActionListener(submitButtonListener);
    }

    // button listener inner class for CF012_USESENSITIVECLS__CWE492_SensitiveBad_4 class
    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == submitButton) {
                String urlString = enterUrlTextField.getText();
                URL url = null;
                try {
                    url = new URL(urlString);
                } catch (MalformedURLException e) {
                    System.err.println("Malformed URL: " + urlString);
                }
                if (url != null) {
                    getAppletContext().showDocument(url);
                }
            }
        }
    }
}