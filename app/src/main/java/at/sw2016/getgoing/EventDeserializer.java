package at.sw2016.getgoing;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventDeserializer {
    private File file;

    public EventDeserializer(File file){
        this.file = file;
    }

    public Event deserializeEvent() {
        Event event;
        try {
            FileInputStream fin = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fin);
            event = (Event) ois.readObject();
            ois.close();

            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
