package h12.gui.shapes;

import h12.exceptions.JSONParseException;
import h12.json.JSONArray;
import h12.json.JSONElement;
import h12.json.implementation.node.JSONArrayNode;
import h12.json.implementation.node.JSONNumberNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A helper class for reading and writing colors from/to a JSON file.
 * <p> A color is represented using a {@link JSONArrayNode} and its RGB representation.
 * <p> The JSON Array contains four {@link JSONNumberNode}'s:
 * <p> 1. The red component of the color.
 * <p> 2. The green component of the color.
 * <p> 3. The blue component of the color.
 * <p> 4. The alpha component of the color.
 * <p>
 * <p> For example, the color {@link Color#BLUE} is represented as followed:
 * <pre>
 * [
 *   0,
 *   0,
 *   255,
 *   255
 * ]
 * </pre>
 */
public class ColorHelper {

    /**
     * Converts the given {@link Color} into a {@link JSONArrayNode}.
     *
     * @param color The color to convert.
     * @return A {@link JSONArrayNode} representing the given color.
     * @see Color
     */
    public static JSONArray toJSON(Color color) {
        Objects.requireNonNull(color);

        List<JSONElement> list = new ArrayList<>();
        list.add(new JSONNumberNode(color.getRed()));
        list.add(new JSONNumberNode(color.getGreen()));
        list.add(new JSONNumberNode(color.getBlue()));
        list.add(new JSONNumberNode(color.getAlpha()));

        return new JSONArrayNode(list);
    }

    /**
     * Converts the contents of the given {@link JSONElement} to a {@link Color}.
     *
     * @param jsonElement The {@link JSONElement} to convert.
     * @return The {@link Color} represented by the given {@link JSONElement}.
     * @throws JSONParseException If the given {@link JSONElement} does not represent a valid number.
     */
    public static Color fromJSON(JSONElement jsonElement) throws JSONParseException, NullPointerException {
        try {
            JSONElement[] array = jsonElement.getArray();

            if (array.length != 4) {
                throw new JSONParseException("Invalid format for a Color. Incorrect amount of entries. Expected %d, but was %d"
                    .formatted(4, array.length));
            }


            int r = array[0].getInteger();
            int g = array[1].getInteger();
            int b = array[2].getInteger();
            int a = array[3].getInteger();

            return new Color(r, g, b, a);
        } catch (UnsupportedOperationException | NoSuchElementException exc) {
            throw new JSONParseException("Invalid format for a Color");
        }
    }
}
