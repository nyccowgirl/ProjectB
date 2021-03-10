import java.util.*;

public class ArrayFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity,
 * using array.
 *
 * Trang Hoang
 * Jared Roussel
 */

    private T[] list;
    private int numberOfElements;
    private boolean initialized = false;

    /**
     * Creates and initializes empty list.
     *
     * @param capacity The capacity of the list
     */

    public ArrayFrontBackCappedList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[capacity];  // Unchecked
        list = tempList;
        numberOfElements = 0;
        initialized = true;
    }


    /**
     * Adds an entry to the beginning of the list if it is not full. If the entry is successfully added, entries
     * currently in the list are shifted down, and the list size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the entry was added; otherwise, returns false if array is full.
     */
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


    /**
     * Adds an entry to the end of the list if it is not full. The rest of the list is not impacted, and the list size
     * is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the entry was added; otherwise, returns false if array is full.
     */
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


    /**
     * Removes an entry from the beginning of the list if the list is not empty. The remaining entries are shifted
     * forwards, and the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
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


    /**
     * Removes an entry from the end of the list if the list is not empty. The rest of the list is not impacted, and
     * the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
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


    /**
     * Removes all entries from the list if the list is not empty.
     *
     * Postcondition: List is empty with list size as 0.
     */
    @Override
    public void clear() {
        checkInitialization();

        while (!(isEmpty())) {
            removeBack();
        }
    }


    /**
     * Retrieves the entry at a certain position in the list, after determining if the position is valid.
     *
     * @param givenPosition An integer that indicates the position of the desired entry.
     * @return A reference to the indicated entry or null if the index is out of bounds.
     */
    @Override
    public T getEntry(int givenPosition) {
        checkInitialization();
        T result = null;

        if (validPosition(givenPosition)) { // Assertion: list is not empty
            result = list[givenPosition];
        }

        return result;
    }


    /**
     * Determines the position in the list of a given entry. If the entry appears more than once, the first index
     * is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return The first position that the entry was found or -1 if the object is not found.
     */
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


    /**
     * Determines the position in the list of a given entry. If the entry appears more than once, the last index
     * is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return The last position that the entry was found or -1 if the object is not found.
     */
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


    /**
     * Determines whether an entry is in the list.
     *
     * @param anEntry the object to search for in the list.
     * @return True if the entry is in the list; otherwise, returns false if list is empty or entry is not found.
     */
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


    /**
     * Retrieves length of list.
     *
     * @return Integer number of entries currently in the list.
     */
    @Override
    public int size() {
        return numberOfElements;
    }


    /**
     * Determines if the list is empty.
     *
     * @return True if the list is empty; otherwise, returns false if the list contains one or more entries.
     */
    @Override
    public boolean isEmpty() {
        return (numberOfElements == 0);
    }


    /**
     * Determines if the list is full.
     *
     * @return True if the list is full; otherwise, returns false.
     */
    @Override
    public boolean isFull() {
        return (numberOfElements >= list.length);
    }


    /**
     * Retrieves number of entries and capacity of list.
     *
     * @return String representation of the list, with number of elements and capacity of the list.
     */
    @Override
    public String toString() {
        return  "size=" + numberOfElements + "; capacity=" + list.length + ";\t" +
                Arrays.toString(toArray());
    }

    /*
    ****************
    PRIVATE METHODS:
    ****************
    */

    /**
     * Checks if list is properly initialized.
     *
     * @throws SecurityException if this object is not initialized.
     */
    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("ArrayFrontBackCappedList object is not initialized properly.");
        }
    }


    /**
     * Makes room for a new entry in the front of the list.
     *
     * Precondition: numberOfElements is list's length before addition; checkInitialization has been called.
     */
    private void makeRoom() {

        int firstIndex = 0;
        int lastIndex = numberOfElements;

        // Move each entry to next higher index, starting at end of
        // list and continuing until the entry at the beginning of list is moved
        for (int index = lastIndex - 1; index >= firstIndex; index--) {
            list[index + 1] = list[index];
        }
    }


    /**
     * Shifts entries that are beyond the entry to be removed to the next lower position.
     * Precondition: numberOfElements is list's length before removal; checkInitialization has been called.
     */

    private void removeGap() {

        int removedIndex = 0;
        int lastIndex = numberOfElements;

        for (int index = removedIndex; index < (lastIndex - 1); index++) {
            list[index] = list[index + 1];
        }
    }


    /**
     * Determines if given position is a valid position within the bounds of the list.
     *
     * @param position The position in the list
     * @return True if the position is valid; otherwise, returns false.
     */
    private boolean validPosition(int position) {
        return position >= 0 && position < numberOfElements;
    }


    /**
     * Casts the list to an array containing the entries in the list.
     *
     * @return An array containing all the elements in the list.
     */
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
