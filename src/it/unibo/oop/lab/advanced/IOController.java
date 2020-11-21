package it.unibo.oop.lab.advanced;

import java.io.IOException;

public interface IOController {

    int getMax();

    int getMin();

    int getAttempts();

    void readSettings() throws IOException;

}
