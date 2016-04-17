package at.sw2016.getgoing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventSerializer {
    private File file;

    public EventSerializer(File file){
        this.file = file;
    }

    public void serializeEvent(Event event) {
        try{
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(event);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
