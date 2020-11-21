package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public final class IOControllerImpl implements IOController {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_CONFIG_PATH = System.getProperty("user.dir")
            + SEPARATOR
            + "res"
            + SEPARATOR
            + "config.yml";
    private static final int DEFAULT_MAX = 100;
    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_ATTEMPTS = 10;

    private int maxNumber;
    private int minNumber;
    private int attempts;

    public IOControllerImpl() {
        this.maxNumber = DEFAULT_MAX;
        this.minNumber = DEFAULT_MIN;
        this.attempts = DEFAULT_ATTEMPTS;
    }

    public void readSettings() throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(DEFAULT_CONFIG_PATH));
            boolean someInitializationFailed = false;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                StringTokenizer st = new StringTokenizer(line, ": ");
                String config = st.nextToken();
                if (config.equalsIgnoreCase("minimum")) {
                    this.minNumber = Integer.parseInt(st.nextToken());
                } else if (config.equalsIgnoreCase("maximum")) {
                    this.maxNumber = Integer.parseInt(st.nextToken());
                } else if (config.equalsIgnoreCase("attempts")) {
                    this.attempts = Integer.parseInt(st.nextToken());
                } else {
                    someInitializationFailed = true;
                }
            }
            br.close();
            if (someInitializationFailed) {
                throw new IOException();
            }
        } catch (final IOException e) {
            throw e;
        }
    }

    public int getMax() {
        return this.maxNumber;
    }

    public int getMin() {
        return this.minNumber;
    }

    public int getAttempts() {
        return this.attempts;
    }

}
