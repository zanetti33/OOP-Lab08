package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public final class DrawNumberViewFileOutput implements DrawNumberView {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String PATH = System.getProperty("user.dir")
            + SEPARATOR
            + "res"
            + SEPARATOR
            + "log.txt";

    private final PrintStream ps;

    public DrawNumberViewFileOutput() throws FileNotFoundException {
        this.ps = new PrintStream(new FileOutputStream(new File(PATH)));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        this.ps.println("The number is incorrect");
    }

    @Override
    public void result(final DrawResult res) {
        this.ps.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        this.ps.println("Limit reached... You lost!");
    }

    @Override
    public void displayError(final String message) {
        this.ps.println("Error: " + message);
    }

}
