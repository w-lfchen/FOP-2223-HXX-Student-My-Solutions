package h12.gui.shapes;

import h12.exceptions.JSONParseException;
import h12.json.JSONElement;
import h12.json.JSONObject;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class JSONToShapeConverter {

    /**
     * Converts the content of the given {@link JSONObject} to a {@link MyPolygon}.
     *
     * @param element The {@link JSONElement} to convert.
     * @return The {@link MyPolygon} represented by the given {@link JSONObject}.
     * @throws JSONParseException If the given {@link JSONElement} does not represent a valid {@link MyPolygon}.
     * @see ColorHelper#fromJSON(JSONElement)
     * @see MyPolygon#toJSON() For the expected content of the {@link JSONElement}
     */
    public MyPolygon polygonFromJSON(JSONElement element) throws JSONParseException { // TODO: test
        try {
            int edges = element.getValueOf("edges").getInteger();
            // stream the contents of the array, convert the elements, then collect them
            List<Integer> x = Arrays.stream(element.getValueOf("x").getArray()).map(JSONElement::getInteger).toList();
            List<Integer> y = Arrays.stream(element.getValueOf("y").getArray()).map(JSONElement::getInteger).toList();
            Color borderColor = ColorHelper.fromJSON(element.getValueOf("borderColor"));
            Color fillColor = ColorHelper.fromJSON(element.getValueOf("fillColor"));
            return new MyPolygon(x, y, fillColor, borderColor, edges);
            // throw different exception
        } catch (UnsupportedOperationException | NoSuchElementException garbage) {
            throw new JSONParseException("Invalid MyShape format!");
        }
    }

    /**
     * Converts the content of the given {@link JSONObject} to a {@link MyCircle}.
     *
     * @param element The {@link JSONElement} to convert.
     * @return The {@link MyCircle} represented by the given {@link JSONObject}.
     * @throws JSONParseException If the given {@link JSONObject} does not represent a valid {@link MyCircle}.
     * @see ColorHelper#fromJSON(JSONElement)
     * @see MyCircle#toJSON() For the expected content of the {@link JSONElement}
     */
    public MyCircle circleFromJSON(JSONElement element) throws JSONParseException {
        try {
            int x = element.getValueOf("x").getInteger();
            int y = element.getValueOf("y").getInteger();
            int radius = element.getValueOf("radius").getInteger();

            Color borderColor = ColorHelper.fromJSON(element.getValueOf("borderColor"));
            Color fillColor = ColorHelper.fromJSON(element.getValueOf("fillColor"));

            return new MyCircle(x, y, radius, fillColor, borderColor);
        } catch (UnsupportedOperationException | NoSuchElementException exc) {
            throw new JSONParseException("Invalid MyShape format!");
        }
    }

    /**
     * Converts the content of the given {@link JSONObject} to a {@link CustomLine}.
     *
     * @param element The {@link JSONElement} to convert.
     * @return The {@link CustomLine} represented by the given {@link JSONObject}.
     * @throws JSONParseException If the given {@link JSONElement} does not represent a valid {@link CustomLine}.
     * @see ColorHelper#fromJSON(JSONElement)
     * @see CustomLine#toJSON() For the expected content of the {@link JSONElement}
     */
    public CustomLine customLineFromJSON(JSONElement element) throws JSONParseException {
        try {
            Color color = ColorHelper.fromJSON(element.getValueOf("borderColor"));

            List<Integer> x = Arrays.stream(element.getValueOf("x").getArray()).map(JSONElement::getInteger).toList();
            List<Integer> y = Arrays.stream(element.getValueOf("y").getArray()).map(JSONElement::getInteger).toList();

            return new CustomLine(x, y, color);
        } catch (UnsupportedOperationException | NoSuchElementException exc) {
            throw new JSONParseException("Invalid MyShape format!");
        }
    }

    /**
     * Converts the content of the given {@link JSONObject} to a {@link MyRectangle}.
     *
     * @param element The {@link JSONElement} to convert.
     * @return The {@link MyRectangle} represented by the given {@link JSONObject}.
     * @throws JSONParseException If the given {@link JSONObject} does not represent a valid {@link MyRectangle}.
     * @see ColorHelper#fromJSON(JSONElement)
     * @see MyRectangle#toJSON() For the expected content of the {@link JSONElement}
     */
    public MyRectangle rectangleFromJSON(JSONElement element) throws JSONParseException {
        try {
            int x = element.getValueOf("x").getInteger();
            int y = element.getValueOf("y").getInteger();
            int height = element.getValueOf("height").getInteger();
            int width = element.getValueOf("width").getInteger();

            Color borderColor = ColorHelper.fromJSON(element.getValueOf("borderColor"));
            Color fillColor = ColorHelper.fromJSON(element.getValueOf("fillColor"));

            return new MyRectangle(x, y, height, width, fillColor, borderColor);
        } catch (UnsupportedOperationException | NoSuchElementException exc) {
            throw new JSONParseException("Invalid MyShape format!");
        }
    }
}
