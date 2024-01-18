package Items;

import living.Entity;

import java.util.ArrayList;

public class Furniture extends Item{
    Entity[] places;
    int size;
    public Furniture(double weight, int size) {
        super(weight);
        places = new Entity[size];
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Entity[] getPlaces() {
        return places;
    }
    public void takePlace(Entity entity, int slot, boolean mute) {
        if (places[slot - 1] == null) {
            places[slot - 1] = entity;
            entity.setLocation(this.getLocation());
            if (!mute) {
                System.out.printf("%s занял скамейку, он сидит на %s-м месте\n", entity.toString(), slot);
            }
        } else {
            if (!mute) {
                System.out.printf("%s хотел занять место на скамейке, он там сидит %s на %s-м месте\n", entity.toString(), places[slot - 1].toString(), slot);
            }
        }
    }

    public void takeSomePlace(Entity entity, boolean mute) {
        for (int i = 0; i < size; i++) {
            if (places[i] != null) {
                if (!mute) {
                    System.out.printf("%s занял скамейку, он сидит на %s-м месте\n", entity.toString(), i + 1);
                }
                places[i] = entity;
                return;
            }
        }
        if (!mute) {
            System.out.printf("%s не смог найти место на скамейке(\n", entity.toString());
        }
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            if (this.places[i] == null) {
                return false;
            }
        }
        return true;
    }
    public boolean isEmpty() {
        for (int i = 0; i < size; i++) {
            if (this.places[i] != null) {
                return false;
            }
        }
        return true;
    }
}
