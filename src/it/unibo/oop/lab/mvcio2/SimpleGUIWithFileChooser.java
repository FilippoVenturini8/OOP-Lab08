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
     * graphical interface. Suggestion: use a second JPanel with a second
     * BorderLayout, put the panel in the North of the main panel, put the text
     * field in the center of the new panel and put the button in the line_end of
     * the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the current
     * selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should use
     * the method showSaveDialog() to display the file chooser, and if the result is
     * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
     * the Controller the file chosen. If CANCEL_OPTION is returned, then the
     * program should do nothing. Otherwise, a message dialog should be shown
     * telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to update
     * the UI: in this example the UI knows when should be updated, so try to keep
     * things separated.
     */
    private final JFrame frame = new JFrame();

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUIWithFileChooser() {
        final Controller controller = new Controller();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea saveTxtArea = new JTextArea();
        canvas.add(saveTxtArea, BorderLayout.CENTER);
        final JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.saveOnCurrent(saveTxtArea.getText());
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage());
                }
            }
        });

        //Second part of the GUI

        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());
        canvas.add(secondPanel, BorderLayout.NORTH);
        final JTextArea browseTxtArea = new JTextArea(controller.getPath());
        secondPanel.add(browseTxtArea, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse");
        secondPanel.add(browse, BorderLayout.LINE_END);

        browseTxtArea.setEditable(false);
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(fc) == JFileChooser.APPROVE_OPTION) {
                    controller.setCurrent(fc.getSelectedFile());
                    browseTxtArea.setText(controller.getPath());
                } else if (fc.showSaveDialog(fc) == JFileChooser.CANCEL_OPTION) {
                    return;
                } else {
                    JOptionPane.showMessageDialog(frame, "Error in choosing the file!");
                }
            }

        });

        frame.setTitle("My first GUI");
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Make the frame half the resolution of the screen. This very method is enough
         * for a single screen setup. In case of multiple monitors, the primary is
         * selected.
         * 
         * In order to deal coherently with multimonitor setups, other facilities exist
         * (see the Java documentation about this issue). It is MUCH better than
         * manually specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this flag
         * makes the OS window manager take care of the default positioning on screen.
         * Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }


}
