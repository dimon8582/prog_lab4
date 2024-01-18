package living;

import Exceptions.BusySlotException;
import Exceptions.MoreItemsThanSizeException;
import Exceptions.SlotAlreadyEmptyException;
import Exceptions.SlotOutOfSizeException;
import Items.Item;
import interfaces.Walkable;

import java.util.ArrayList;

public abstract class Creation extends Entity implements Walkable {
    Inventory inventory;
    public Creation(String name, int age) {
        super(name, age);
        inventory = new Inventory(5);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public class Inventory {
        Item[] items;
        int size;
        public Inventory(int size) {
            this.size = size;
            items = new Item[size];
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) throws MoreItemsThanSizeException {
            int itemCounter = 0;
            for (int i = 0; i < this.size; i++) {
                if (items[i] != null) {
                    itemCounter++;
                }
            }
            if (itemCounter > size) {
                throw new MoreItemsThanSizeException("В инвентаре больше предметов, чем заданный размер!");
            } else {
                this.size = size;
            }
        }
        public void setItem(Item item, int slot) throws BusySlotException{
            if (items[slot] == null) {
                items[slot] = item;
            } else {
                throw new BusySlotException("В этом слоте уже есть предмет!");
            }
        }

        public void clearSlot(int slot) throws SlotOutOfSizeException, SlotAlreadyEmptyException {
            if (slot > size - 1) {
                throw new SlotOutOfSizeException("Заданный слот больше размера инвентаря!");
            } else if (items[slot] == null) {
                throw new SlotAlreadyEmptyException("Заданный слот уже свободен, его нельзя очистить!");
            } else {
                items[slot] = null;
                System.out.printf("Слот %s в инвентаре %s очищен\n", slot, Creation.this.toString());
            }
        }

        public void clearAll() {
            for (int i = 0; i < size; i++) {
                items[i] = null;
            }
            System.out.printf("Инвентарь %s очищен\n", Creation.this.toString());
        }
    }
}
