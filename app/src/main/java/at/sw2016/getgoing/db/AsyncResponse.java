package at.sw2016.getgoing.db;

import org.json.JSONArray;

/**
 * Created by peisn_000 on 17.06.2016.
 */
public interface AsyncResponse {

    void processFinish(JSONArray output);
}
