package at.sw2016.getgoing;

/**
 * Created by Lukas on 25.05.2016.
 */
public enum EventType {

    BIRTHDAY (R.drawable.birthday),
    DEFOULT (R.drawable.defaultevent),
    COCKTAIL(R.drawable.cocktail),
    GRILL (R.drawable.grill);

    private final int path;

    EventType(int path)
    {
        this.path = path;
    }

    public int getPath() {
        return path;
    }
}
