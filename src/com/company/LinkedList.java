package com.company;

import java.util.Iterator;

public class LinkedList<T> {
    private Node head;
    private Node tail;
    private int size = 0;


    private class Node {
        T value;
        Node next;

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

    }

    public void addFirst(T value) {
        head = new Node(value, head);
        if (isEmpty())
            tail = head;
        size++;
    }

    public void addLast(T value) {
        Node temp = new Node(value, null);
        if (isEmpty()) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    public T getValueByIndex(int index) {
        return getNodeByIndex(index).value;
    }

    private Node getNodeByIndex(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private int getIndexOfMinValue() {
        int indexOfMin = 0;
        Node nodeWithMinValue = head;
        Node node = head.next;
        for (int i = 1; i < size; i++) {
            if ((int) nodeWithMinValue.value > (int) node.value) {
                indexOfMin = i;
                nodeWithMinValue = node;
            }
            node = node.next;
        }
        return indexOfMin;
    }

    public int getSize() {
        return size;
    }

    public void insert(int index, T value) {
        if (index == 0)
            addFirst(value);
        else {
            if (index == size)
                addLast(value);
            else {
                if (index > size || index < 0)
                    throw new IndexOutOfBoundsException();
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(value, prev.next);
                size++;
            }
        }
    }

    public void removeFirst() throws Exception {
        if (size == 0)
            throw new Exception("Нельзя удалить элемент, т.к. список пуст!");
        if (size == 1)
            clear();
        else {
            head = head.next;
            size--;
        }
    }

    public void removeLast() throws Exception {
        if (size == 0)
            throw new Exception("Нельзя удалить элемент, т.к. список пуст!");
        if (size == 1)
            clear();
        else {
            tail = getNodeByIndex(size - 2);
            tail.next = null;
            size--;
        }
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public Node maxNode(){
        Node current;
        double minValue = Double.MIN_VALUE;
        int j = 0;
        for (int i = 0; i < getSize(); i++) {
            if (minValue < (double)(getValueByIndex(i))) {
                minValue = (double) getValueByIndex(i);
                j = i;
            }
        }
        current = getNodeByIndex(j);
        return current;
    }
    public String maxNodeToString(Node node){
        return node.toString();
    }
    public int getIndexByNode(Node node){
        Node current = head;
        for(int i = 0; i < size; i++){
            if (node == current){
                return i;
            }
            current = current.next;
        }
        return 0;

    }

    public void removeByIndex(int index) throws Exception {
        if (index == 0)
            removeFirst();
        else {
            if (index == size - 1)
                removeLast();
            else {
                if (index >= size || index < 0)
                    throw new NullPointerException("Указан недействительный индекс");
                Node node = getNodeByIndex(index - 1);
                node.next = node.next.next;
                size--;
            }
        }
    }

    public String getListToString() {
        String result = "";
        Iterator<T> itr = iterator();
        while (itr.hasNext()) {
            result += itr.next().toString() + " ";
        }
        return result;
    }

    private class MyLinkedIteration implements Iterator<T> {
        private Node current;

        public MyLinkedIteration(Node head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T result = current.value;
            current = current.next;
            return result;
        }
    }

    public Iterator<T> iterator() {
        return new MyLinkedIteration(head);
    }

}