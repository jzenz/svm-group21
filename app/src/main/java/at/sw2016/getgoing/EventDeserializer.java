package at.sw2016.getgoing;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventDeserializer {

    public List<Event> deserializeEvents(ByteArrayInputStream bais) {
        List<Event> events;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            events = (List<Event>) ois.readObject();
            ois.close();
            return events;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
