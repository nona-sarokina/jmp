package module6.task2;

import java.util.List;

public class ListAdapter<E> implements FIFO<E>{
    List<E> list;

    public ListAdapter(List<E> list) {
        this.list = list;
    }

    @Override
    public void push(E object) {
        list.add(object);
    }

    @Override
    public E pop() {
        E element = list.get(list.size() - 1);
        list.remove(element);
        return element;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
