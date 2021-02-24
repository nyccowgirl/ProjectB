import java.util.*;

public class ArrayFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity.
 *
 * @author Trang Hoang
 * @version 1.2
 */

    private T[] list;
    private int numberOfElements;
    private boolean initialized = false;

    public ArrayFrontBackCappedList(int capacity) {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[capacity];  // Unchecked
        list = tempList;
        numberOfElements = 0;
        initialized = true;
    }

    @Override
    public boolean addFront(T newEntry) {
        checkInitialization();
        boolean result = true;

        if (isFull()) {
            result = false;
        } else { // Assertion: result is true
            makeRoom();
            list[0] = newEntry;
            numberOfElements++;
        }

        return result;
    }

    @Override
    public boolean addBack(T newEntry) {
        checkInitialization();
        boolean result = true;

        if (isFull()) {
            result = false;
        } else { // Assertion: result is true
            list[numberOfElements] = newEntry;
            numberOfElements++;
        }

        return result;
    }

    @Override
    public T removeFront() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list[0]; // Get entry to be removed in front of list

            // Move subsequent elements forwards
            removeGap();
            numberOfElements--;
        }

        return result;
    }

    @Override
    public T removeBack() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list[numberOfElements - 1]; // Get entry to be removed from back of list
            numberOfElements--;
        }

        return result;
    }

    @Override
    public void clear() {
        checkInitialization();

        while (!(isEmpty())) {
            removeBack();
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        checkInitialization();
        T result = null;

        if (validPosition(givenPosition)) { // Assertion: list is not empty
            result = list[givenPosition];
        }

        return result;
    }

    @Override
    public int indexOf(T anEntry) {
        checkInitialization();
        int position = -1;

        if (!(isEmpty())) {
            int index = 0;

            // Searching from front of list
            do {
                if (anEntry.equals(list[index])) {
                    position = index;
                }
                index++;
            } while ((position < 0) && (index < numberOfElements));
        }

        return position;
    }

    @Override
    public int lastIndexOf(T anEntry) {
        checkInitialization();
        int position = -1;

        if (!(isEmpty())) {
            int index = numberOfElements - 1;

            // Searching from back of list
            do {
                if (anEntry.equals(list[index])) {
                    position = index;
                }
                index--;
            } while ((position < 0) && (index >= 0));
        }

        return position;
    }

    @Override
    public boolean contains(T anEntry) {
        checkInitialization();
        boolean inList = false;
        int index = 0;

        if (!(isEmpty())) {
            // Searching from front of list
            while (!(inList) && (index < numberOfElements)) {
                if (anEntry.equals(list[index])) {
                    inList = true;
                }
                index++;
            }
        }

        return inList;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfElements == 0);
    }

    @Override
    public boolean isFull() {
        return (numberOfElements >= list.length);
    }

    @Override
    public String toString() {
        return  "size=" + numberOfElements + "; capacity=" + list.length + ";\t" +
                Arrays.toString(toArray());
    }

    /*
    PRIVATE METHODS:
     */

    // Throws an exception if this object is not initialized.
    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("ArrayFrontBackCappedList object is not initialized properly.");
        }
    }

//    // Returns true if the list is full, or false if not.
//    private boolean isListFull() {  // TBD: Duplicative of isFull()?
//        return numberOfElements >= list.length;
//    }

    // Makes room for a new entry in the front of the list.
    // Precondition: numberOfElements is list's length before addition;
    // checkInitialization has been called.
    private void makeRoom() {
//        DELETE: assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);

        int firstIndex = 0;
        int lastIndex = numberOfElements;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at the beginning of list is moved
        for (int index = lastIndex - 1; index >= firstIndex; index--) {
            list[index + 1] = list[index];
        }
    }

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: numberOfElements is list's length before removal;
    // checkInitialization has been called.
    private void removeGap() {
//        DELETE: assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

        int removedIndex = 0;
        int lastIndex = numberOfElements;

        for (int index = removedIndex; index < (lastIndex - 1); index++) {
            list[index] = list[index + 1];
        }

        // TBD: numberOfElements will be reduced -> make last element null -> make consistent with remove from front if so
//        list[lastIndex - 1] = null;
    }

    private boolean validPosition(int position) {
        return position >= 0 && position < numberOfElements;
    }

    private T[] toArray() {
        checkInitialization();

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfElements]; // Unchecked cast
        for (int index = 0; index < numberOfElements; index++) {
            result[index] = list[index];
        }

        return result;
    }

}
