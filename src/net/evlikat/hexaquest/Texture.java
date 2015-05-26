package net.evlikat.hexaquest;

/**
 * Created by RSProkhorov on 26.05.2015.
 */
public enum Texture {
    ;

    private final String filename;

    private Texture(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
