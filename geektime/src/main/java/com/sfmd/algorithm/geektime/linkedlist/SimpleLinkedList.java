package com.sfmd.algorithm.geektime.linkedlist;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * 带头单链表
 *
 */
public class SimpleLinkedList<E> implements List<E> {

    private int size = 0;

    private Node<E> head;

    private Node<E> sentinel = new Node<E>();

    public SimpleLinkedList() {

        head = new Node<>();
        head.next = sentinel;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return 0 == size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) == -1;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {

        Object[] array = new Object[size];

        Iterator<E> iterator = iterator();
        int index = 0;
        while(iterator.hasNext()){
            array[index] = iterator.next();
        }

        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[])Array.newInstance(a.getClass().getComponentType(), size);
        }

        Object[] result = a;
        Iterator<E> iterator = iterator();
        int index = 0;
        while(iterator.hasNext()){
            result[index] = iterator.next();
        }
        if (result.length > size){
            result[size] = 0;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>();
        node.item = e;
        node.next = head.next;
        head.next = node;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node<E> current = head; current.next != sentinel; current = current.next ){
            if (Objects.equals(o, current.next.item)){
                current.next = current.next.next;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        Node<E> current = stepIntoPosition(index);
        return current.next.item;
    }

    private Node<E> stepIntoPosition(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(index + "");
        }
        int i = 0;
        Node<E> current = head;
        for (; i < index; current = current.next, i++) {
        }
        return current;
    }

    @Override
    public E set(int index, E element) {
        Node<E> current = stepIntoPosition(index);
        E oldValue = current.next.item;
        current.next.item = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
