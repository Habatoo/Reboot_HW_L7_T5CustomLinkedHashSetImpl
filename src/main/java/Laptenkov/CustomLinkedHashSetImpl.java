package Laptenkov;

import java.util.Objects;

/**
 * Класс {@link CustomLinkedHashSetImpl<T>}, реализует множество на хеш-таблицы.
 * Класс {@link CustomLinkedHashSetImpl<T>} реализует интерфейс {@link CustomLinkedHashSet<T>}.
 * Класс {@link CustomLinkedHashSetImpl<T>} может хранить объекты любого типа
 * Методы toString & toArray должны возвращают элементы в том порядке,
 * в котором элементы были добавлены в множество.
 *
 * @param <T>
 */
public class CustomLinkedHashSetImpl<T> implements CustomLinkedHashSet<T>{

    /**
     * Контейнер для хранения объекта {@link CustomLinkedHashSetImpl<T>}
     * @param <T>
     */
    private static class Node<T> {

        T item;
        int hash;

        Node<T> next;
        Node<T> prev;
        Node<T> after;
        Node<T> before;

        public Node(T item, int hash) {
            this.item = item;
            this.hash = hash;
        }
    }

    private Node<T>[] table = new Node[10];
    private Node<T> head, tail;
    private int size;

    /**
     * Конструктор пустого объекта {@link CustomLinkedHashSetImpl<T>}.
     */
    CustomLinkedHashSetImpl() {

    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomLinkedHashSetImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#isEmpty()} возвращает булево значение
     * при проверке объекта {@link CustomLinkedHashSetImpl} на пустоту.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#add(Object)} возвращает булево значение
     * при попытке добавления объекта в {@link CustomLinkedHashSetImpl}.
     *
     * @param newItem объект типа Т для добавления.
     * @return возвращает <code>true</code> если добавление успешно,
     * возвращает <code>false</code> если добавление не удалось.
     */
    @Override
    public boolean add(T newItem) {

        int bucketId = getBucket(newItem);
        int newItemHash = getItemHash(newItem);

        Node<T> tmp = table[bucketId];
        Node<T> prevTmp = null;

        while (tmp != null) {

            if (newItemHash == tmp.hash && Objects.equals(newItem, tmp.item)) {
                // hash code & equals
                return false;
            }
            prevTmp = tmp;
            tmp = tmp.next;
        }

        Node<T> insertedNode = new Node<>(newItem, newItemHash);

        if (prevTmp == null) {
            table[bucketId] = insertedNode;
        } else {
            prevTmp.next = insertedNode;
            insertedNode.prev = prevTmp;
        }

        if (tail == null) {
            head = tail = insertedNode;
            insertedNode.before = null;
        } else {
            tail.after = insertedNode;
            insertedNode.before = tail;
            tail = insertedNode;
        }

        size++;
        return true;
    }

    /**
     * Вспомогательный метод {@link CustomLinkedHashSetImpl#getItemHash(Object)}
     * возвращает целое число - hashCode переданного
     * объекта в {@link CustomLinkedHashSetImpl}.
     *
     * @param newItem объект типа Т для хеширования.
     * @return целое число int.
     */
    private int getItemHash(T newItem) {
        if (newItem == null) {
            return 0;
        }
        return newItem.hashCode();
    }

    /**
     * Вспомогательный метод {@link CustomLinkedHashSetImpl#getBucket(Object)}
     * возвращает целое число - номер бакета для размещения переданного
     * объекта в {@link CustomLinkedHashSetImpl}.
     *
     * @param newItem объект типа Т для определия бакета.
     * @return целое число int.
     */
    private int getBucket(T newItem) {

        if (newItem == null) {
            return 0;
        }
        return newItem.hashCode() % (table.length - 1) + 1;
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#remove(Object)} возвращает булево значение
     * при попытке удаления объекта в {@link CustomLinkedHashSetImpl}.
     *
     * @param item объект типа Т для удаления.
     * @return возвращает <code>true</code> если удаление успешно,
     * возвращает <code>false</code> если удаление не удалось.
     */
    @Override
    public boolean remove(T item) {

        if (size == 0) {
            return false;
        }

        if (null == item && table[0] != null) {
            table[0] = null;
            size--;
            return true;
        }

        int hash = getItemHash(item);
        int removeItemBucket = getBucket(item);

        Node<T> tmp = head;
        Node<T> tmpPrev = null;

        boolean flag = false;

        while (tmp != null) {
            if (tmp.hash == hash && tmp.item.equals(item))  {
                flag = true;
                break;
            }
            tmpPrev = tmp;
            tmp = tmp.after;
        }

        if (!flag) {
            return false;
        }

        if (tmpPrev == null) {
            table[removeItemBucket] = null;
        } else {
            tmpPrev.next = tmp.next;
        }

        if (head == tmp && tail == tmp) {
            head = tail = null;
        } else if (head == tmp) {
            head = tmp.after;
        } else if (tail == tmp) {
            tail = tmpPrev;
        } else {
            tmpPrev.after = tmp.after;
        }

        size--;

        return true;
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#contains(Object)} возвращает булево значение
     * при проверке наличия объекта в {@link CustomLinkedHashSetImpl}.
     *
     * @param item объект типа Т для проверки.
     * @return возвращает <code>true</code> если объект присутствует,
     * возвращает <code>false</code> если объект отсутствует.
     */
    @Override
    public boolean contains(T item) {
        int bucketId = getBucket(item);
        int newItemHash = getItemHash(item);
        Node<T> tmp = table[bucketId];

        while (tmp != null) {
            if (newItemHash == tmp.hash && Objects.equals(item, tmp.item)) {
                return true;
            }
            tmp = tmp.next;
        }

        return false;
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl#toString()}
     * возвращает строковое представление дерева {@link CustomLinkedHashSetImpl}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ]
     * или [ ] если дерево пустое.
     */
    @Override
    public String toString() {
        StringBuilder cb = new StringBuilder();

        cb.append("[");
        Node<T> tmp = head;
        while (tmp != null) {
            cb.append(" " + tmp.item);
            tmp = tmp.after;
        }
        cb.append(" ]");

        return cb.toString();
    }

    /**
     * Метод {@link CustomLinkedHashSetImpl<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomLinkedHashSetImpl<T>}.
     *
     * @return объект типа {@link CustomLinkedHashSetImpl<T>}.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];
        Node<T> tmp = head;
        int i = 0;

        while (tmp != null) {
            newData[i++] = tmp.item;
            tmp = tmp.after;
        }
        return newData;
    }
}