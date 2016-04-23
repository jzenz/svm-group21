package at.sw2016.getgoing;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventDeserializer {

    public Event deserializeEvent(ByteArrayInputStream bais) {
        Event event;

        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            event = (Event) ois.readObject();
            ois.close();
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
