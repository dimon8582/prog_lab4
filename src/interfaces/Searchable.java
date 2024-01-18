package interfaces;

import technical.Location;

public interface Searchable {
    void search(Location searcherLoc, Location searchingLoc);
    boolean getResult();
    void setResult(boolean result);
}
