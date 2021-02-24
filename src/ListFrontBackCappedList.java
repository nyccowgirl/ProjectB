import java.util.*;

public class ListFrontBackCappedList<T> implements FrontBackCappedListInterface<T> {

    private List<T> list;
    private boolean initialized = false;
    private final int capacity;

    public ListFrontBackCappedList(int capacity) {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        List<T> tempList = new ArrayList<>(capacity);  // Unchecked
        list = tempList;
//        numberOfElements = 0;
        this.capacity = capacity;
        initialized = true;
    }

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

    @Override
    public T removeFront() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list.remove(0);
        }

        return result;
    }

    @Override
    public T removeBack() {
        checkInitialization();
        T result = null;

        if (!(isEmpty())) {
            result = list.remove(list.size() - 1);
        }
        return result;
    }

    @Override
    public void clear() {
        checkInitialization();

        if (!(isEmpty())) {
            list.clear();
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        checkInitialization();
        T result = null;

        if (validPosition(givenPosition)) { // Assertion: list is not empty
            result = list.get(givenPosition);
        }

        return result;
    }

    @Override
    public int indexOf(T anEntry) {
        checkInitialization();

        return list.indexOf(anEntry);
    }

    @Override
    public int lastIndexOf(T anEntry) {
        checkInitialization();

        return list.lastIndexOf(anEntry);
    }

    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return (list.size() >= capacity);
    }

    @Override
    public String toString() {
        return  "size=" + list.size() + "; capacity=" + capacity + ";\t" +
                list.toString();
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

    private boolean validPosition(int position) {
        return position >= 0 && position < list.size();
    }
}
