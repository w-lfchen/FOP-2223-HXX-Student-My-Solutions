package h12.json;

import h12.exceptions.JSONParseException;
import h12.exceptions.JSONWriteException;
import h12.ioFactory.FileSystemIOFactory;
import h12.ioFactory.IOFactory;
import h12.json.parser.JSONParser;
import h12.json.parser.JSONParserFactory;
import h12.json.parser.implementation.node.JSONNodeParserFactory;

import java.io.*;

/**
 * A class for handling JSON files.
 */
public class JSON {

    private JSONParserFactory parserFactory = new JSONNodeParserFactory();
    private IOFactory ioFactory = new FileSystemIOFactory();

    /**
     * Writes a string representation of the given {@link JSONElement} object to the given file.
     *
     * @param fileName The name of the file to write the root {@link JSONElement} to.
     * @param root     The root {@link JSONElement} to write to the file.
     * @throws JSONWriteException If an exception occurs while writing to the JSON file or the {@link #ioFactory} does not support writing.
     */
    public void write(String fileName, JSONElement root) throws JSONWriteException {
        if (!ioFactory.supportsWriter()) throw new JSONWriteException("The current ioFactory does not support writing!");
        // try-with-ressource
        try (BufferedWriter writer = ioFactory.createWriter(fileName)) {
            root.write(writer, 0);
        } catch (IOException exc) {
            throw new JSONWriteException(exc.getMessage());
        }
    }

    /**
     * Reads the content of the given JSON file and parses the content of it.
     *
     * @param fileName The fileName to the JSON file to parse.
     * @return A {@link JSONElement} that contains the content of the parsed file.
     * @throws JSONParseException If an exception occurs while trying to parse the JSON file or the {@link #ioFactory} does not support reading.
     */
    public JSONElement parse(String fileName) throws JSONParseException {
        if (!ioFactory.supportsReader()) throw new JSONParseException("The current ioFactory does not support reading!");
        // again, try-with-ressource
        try (BufferedReader bufferedReader = ioFactory.createReader(fileName);
             LookaheadReader lookaheadReader = new LookaheadReader(bufferedReader)) {
            // try creating a parser and arsing with it
            JSONParser parser = parserFactory.createParser(lookaheadReader);
            return parser.parse();
        } catch (IOException exc) {
            throw new JSONParseException(exc.getMessage());
        }
    }

    /**
     * Sets the default {@link JSONParserFactory} that is used to create a {@link JSONParser}
     *
     * @param parserFactory The new default {@link JSONParserFactory}.
     */
    public void setParserFactory(JSONParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    /**
     * Sets the default {@link IOFactory} that is used to create the {@link Reader} and {@link Writer} for the JSON files.
     *
     * @param ioFactory The new default {@link IOFactory}.
     */
    public void setIOFactory(IOFactory ioFactory) {
        this.ioFactory = ioFactory;
    }
}
