package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public final class ControllerImpl implements Controller {

    private final List<String> history;
    private String nextString;

    public ControllerImpl() {
        this.history = new ArrayList<>();
        this.nextString = null;
    }

    public void setNextString(final String nextString) throws NullPointerException {
        if (nextString == null) {
            throw new NullPointerException();
        }
        this.nextString = nextString;
    }

    public String getNextString() {
        return this.nextString;
    }

    public List<String> getHistory() {
        return List.copyOf(this.history);
    }

    public void print() throws IllegalStateException {
        if (this.nextString == null) {
            throw new IllegalStateException();
        }
        System.out.println(this.nextString);
        this.history.add(this.nextString);
        this.nextString = null;
    }

}
