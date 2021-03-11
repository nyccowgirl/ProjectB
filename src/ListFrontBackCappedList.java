import java.util.*;

public class ListFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

/**
 * A class that implements the ADT list with front or back entry or removal and capped to a given capacity,
 * using List object.
 *
 * Trang Hoang (sect. 933)
 * Jared Roussel (sect. 933)
 * Brent Gannetta (sect. 932)
 */

    private List<T> list;
    private boolean initialized = false;
    private final int capacity;


    /**
     * Creates and initializes empty list.
     *
     * @param capacity The capacity of the list
     */
    public ListFrontBackCappedList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        List<T> tempList = new ArrayList<>(capacity);  // Unchecked
        list = tempList;
        this.capacity = capacity;
        initialized = true;
    }


    /**
     * Adds an entry to the beginning of the list if it is not full.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the entry was added; otherwise, returns false if list is full.
     */
    @Override
    public boolean addFront(T newEntry) {
        checkInitialization();
        boolean result = true;

        if (isFull()) {
            result = false;
        } else { // Assertion: result is true
            list.add(0, newEntry);
        }

        return result;
    }


    /**
     * Adds an entry to the end of the list if it is not full.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True if the entry was added; otherwise, returns false if list is full.
     */
    @Override
    public boolean addBack(T newEntry) {
        checkInitialization();
        boolean result = true;

        if (isFull()) {
            result = false;
        } else { // Assertion: result is true
            list.add(newEntry);
        }

        return result;
    }


    /**
     * Removes an entry from the beginning of the list if the list is not empty.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
    @Override
    public T removeFront() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list.remove(0);
        }

        return result;
    }


    /**
     * Removes an entry from the end of the list if the list is not empty.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
    @Override
    public T removeBack() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list.remove(list.size() - 1);
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

        if (!(isEmpty())) {
            list.clear();
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
            result = list.get(givenPosition);
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

        return list.indexOf(anEntry);
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

        return list.lastIndexOf(anEntry);
    }


    /**
     * Determines whether an entry is in the list.
     *
     * @param anEntry the object to search for in the list.
     * @return True if the entry is in the list; otherwise, returns false if list is empty or entry is not found.
     */
    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }


    /**
     * Retrieves length of list.
     *
     * @return Integer number of entries currently in the list.
     */
    @Override
    public int size() {
        return list.size();
    }


    /**
     * Determines if the list is empty.
     *
     * @return True if the list is empty; otherwise, returns false if the list contains one or more entries.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * Determines if the list is full.
     *
     * @return True if the list is full; otherwise, returns false.
     */
    @Override
    public boolean isFull() {
        return (list.size() >= capacity);
    }


    /**
     * Retrieves number of entries and capacity of list.
     *
     * @return String representation of the list, with number of elements and capacity of the list.
     */
    @Override
    public String toString() {
        return  "size=" + list.size() + "; capacity=" + capacity + ";\t" +
                list.toString();
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
            throw new SecurityException("ListFrontBackCappedList object is not initialized properly.");
        }
    }


    /**
     * Determines if given position is a valid position within the bounds of the list.
     *
     * @param position The position in the list
     * @return True if the position is valid; otherwise, returns false.
     */
    private boolean validPosition(int position) {
        return position >= 0 && position < list.size();
    }
}
