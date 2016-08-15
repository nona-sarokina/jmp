package module6.task2;

import java.util.ArrayList;
import java.util.LinkedList;

/*
Creational Patterns.Task 2: Adapter
Write a program that will expose the work with java.util.List collections through pop() and push() methods
Things to do:
       Write a class that wraps any given List collection
       Implement two methods:
              Push – that appends a given object
              Pop – pulls the last object from the collection
 */
public class Runner {
    public static void main(String[] args) {
        ListAdapter<Integer> arrayListAdapter = new ListAdapter(new ArrayList());
        ListAdapter<Integer> linkedListAdapter = new ListAdapter(new LinkedList());
        for (int i = 0; i < 10; i++) {
            arrayListAdapter.push(i);
            linkedListAdapter.push(i);
        }
        System.out.println(arrayListAdapter);
        System.out.println(linkedListAdapter);
        for (int i = 0; i < 10; i++) {
            System.out.println(arrayListAdapter.pop());
            System.out.println(linkedListAdapter.pop());
        }
        System.out.println(arrayListAdapter);
        System.out.println(linkedListAdapter);
    }
}
