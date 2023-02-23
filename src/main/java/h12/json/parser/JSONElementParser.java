package h12.json.parser;

import h12.exceptions.BadFileEndingException;
import h12.exceptions.JSONParseException;
import h12.json.JSONElement;

import java.io.IOException;

/**
 * An interface for parser for the elements of a JSON file.
 */
public interface JSONElementParser {

    /**
     * Parses the next JSON element of a JSON file.
     *
     * @return the parsed JSON element.
     * @throws IOException        If an {@link IOException} occurs while reading the contents of the reader.
     * @throws JSONParseException If the parsed JSON file is invalid.
     */
    JSONElement parse() throws IOException, JSONParseException;

    /**
     * Checks if the reader contains any more characters to process.
     *
     * @throws IOException            If an {@link IOException} occurs while reading the contents of the reader.
     * @throws BadFileEndingException If the reader contains any characters that yet have to be processed.
     */
    void checkEndOfFile() throws IOException, BadFileEndingException;

}
