package ui.buttons;

import ui.VisualApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectButton extends Button {

    public CreateProjectButton(VisualApp editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JMenuItem("Create A Project");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new CreateProjectButtonListener());
    }

    private class CreateProjectButtonListener implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}