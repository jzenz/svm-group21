package at.sw2016.getgoing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Michael on 17.04.2016.
 */
public class EventSerializer {
    public byte[] serializeEvent(Event event) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(baos);
            oos.writeObject(event);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try{
                if(oos != null)
                    oos.close();
            } catch(IOException ex){

            }
            try {
                baos.close();
            } catch(IOException ex) {

            }
        }
        return null;
    }
}
