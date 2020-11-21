package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;
    private final IOController controller;
    /**
     * @throws FileNotFoundException 
     * 
     */
    public DrawNumberApp() throws FileNotFoundException {
         this.views = List.of(new DrawNumberViewImpl(),
                new DrawNumberViewImpl(),
                new DrawNumberViewFileOutput(),
                new DrawNumberViewStdout());
        this.controller = new IOControllerImpl();
        try {
            this.controller.readSettings();
        } catch (IOException e) {
            this.views.forEach(view -> view.displayError("The config file was missing or bad written,"
                    + "\nso we are using default settings"));
        }
        this.model = new DrawNumberImpl(controller.getMin(), controller.getMax(), controller.getAttempts());
        this.views.forEach(view -> view.setObserver(this));
        this.views.forEach(view -> view.start());
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.views.forEach(view -> view.result(result));
        } catch (IllegalArgumentException e) {
            this.views.forEach(view -> view.numberIncorrect());
        } catch (AttemptsLimitReachedException e) {
            this.views.forEach(view -> view.limitsReached());
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp();
    }

}
