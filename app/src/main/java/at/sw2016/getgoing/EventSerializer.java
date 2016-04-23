package at.sw2016.getgoing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventSerializer {
    public byte[] serializeEvents(List<Event> events) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(baos);
            oos.writeObject(events);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(oos != null)
                    oos.close();
            } catch(IOException ex){}
            try {
                baos.close();
            } catch(IOException ex) {}
        }
        return null;
    }
}
