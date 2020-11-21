package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame();
    private final Controller controller = new Controller();

    private void start() {
        JPanel jp = new JPanel();
        JButton save = new JButton("Save");
        JTextArea textArea = new JTextArea();
        JTextField fileToPrint = new JTextField();
        fileToPrint.setEditable(false);
        fileToPrint.setText(this.controller.getFile());
        JButton browseButton = new JButton("Browse...");
        JPanel browseArea = new JPanel();
        JFileChooser fileChooser = new JFileChooser();
        browseArea.setLayout(new BorderLayout());
        browseArea.add(fileToPrint, BorderLayout.CENTER);
        browseArea.add(browseButton, BorderLayout.LINE_END);
        jp.setLayout(new BorderLayout());
        jp.add(textArea);
        jp.add(save, BorderLayout.SOUTH);
        jp.add(browseArea, BorderLayout.NORTH);
        this.frame.setContentPane(jp);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    SimpleGUIWithFileChooser.this.controller.write(textArea.getText());
                } catch (IOException e1) {
                    System.out.println("Error...");
                    e1.printStackTrace();
                }
            }
        });
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                var result = fileChooser.showSaveDialog(jp);
                if (result == JFileChooser.APPROVE_OPTION) {
                    var newPath = fileChooser.getSelectedFile().getPath();
                    SimpleGUIWithFileChooser.this.controller.setFile(newPath);
                    fileToPrint.setText(newPath);
                    return;
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                JOptionPane.showMessageDialog(fileChooser, "An error has occurred during the file selection");
            }
        });
    }

    public SimpleGUIWithFileChooser() {
        this.start();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        this.frame.setSize(sw / 2, sh / 2);
        this.frame.setLocationByPlatform(true);
        this.frame.setTitle("SimpleGUI");
        this.frame.setVisible(true);
    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }

}
