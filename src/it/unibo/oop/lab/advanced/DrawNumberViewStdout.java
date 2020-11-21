package it.unibo.oop.lab.advanced;

public final class DrawNumberViewStdout implements DrawNumberView {

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
    }

    @Override
    public void start() {
    }

    @Override
    public void numberIncorrect() {
        System.out.println("The number is incorrect");
    }

    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        System.out.println("Limit reached... You lost!");
    }

    @Override
    public void displayError(final String message) {
        System.err.println(message);
    }

}
