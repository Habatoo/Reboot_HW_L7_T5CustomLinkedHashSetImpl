package Laptenkov;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomLinkedHashSetImpl<Object>}.
 *
 * @author habatoo
 */
class CustomLinkedHashSetImplTest {

    private CustomLinkedHashSet<Integer> customEmptyLinkedHashSet;
    private CustomLinkedHashSet<Integer> customNotEmptyLinkedHashSet;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomLinkedHashSetImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyLinkedHashSet = new CustomLinkedHashSetImpl<>();

        customNotEmptyLinkedHashSet = new CustomLinkedHashSetImpl<>();
        customNotEmptyLinkedHashSet.add(10);
        customNotEmptyLinkedHashSet.add(9);
        customNotEmptyLinkedHashSet.add(12);
        customNotEmptyLinkedHashSet.add(13);
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomLinkedHashSetImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyLinkedHashSet = null;
        customNotEmptyLinkedHashSet = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomLinkedHashSetImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию,
     * проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void customHashSetImpl_Test() {
        customEmptyLinkedHashSet = new CustomLinkedHashSetImpl<>();
        Assertions.assertEquals(0, customEmptyLinkedHashSet.size());
        Assertions.assertEquals(
                "[ ]", customEmptyLinkedHashSet.toString());
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#size_Test()}
     * проверяет метод {@link CustomLinkedHashSetImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomLinkedHashSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает величину
     * объекта равную 0, не пустой экземпляр возвращает 6.
     */
    @Test
    void size_Test() {
        Assertions.assertEquals(0, customEmptyLinkedHashSet.size());
        Assertions.assertEquals(4, customNotEmptyLinkedHashSet.size());
    }

    /**
     * Метод  {@link CustomLinkedHashSetImplTest#isEmpty_Test()}
     * проверяет метод {@link CustomLinkedHashSetImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomLinkedHashSetImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    void isEmpty_Test() {
        Assertions.assertEquals(true, customEmptyLinkedHashSet.isEmpty());
        Assertions.assertEquals(false, customNotEmptyLinkedHashSet.isEmpty());
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#addSuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    void addSuccess_Test() {
        Assertions.assertEquals(0, customEmptyLinkedHashSet.size());
        Assertions.assertEquals(true, customEmptyLinkedHashSet.add(90));
        Assertions.assertEquals(1, customEmptyLinkedHashSet.size());
        Assertions.assertEquals(true, customEmptyLinkedHashSet.add(100));
        Assertions.assertEquals(2, customEmptyLinkedHashSet.size());
        Assertions.assertEquals(false, customEmptyLinkedHashSet.add(100));
        Assertions.assertEquals(2, customEmptyLinkedHashSet.size());
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#addFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#add(Object)}.
     * Сценарий, при котором уже существующий объект не успешно
     * добавляется и возвращает <code>true</code>.
     */
    @Test
    void addFail_Test() {
        Assertions.assertEquals(true, customEmptyLinkedHashSet.add(10000));
        Assertions.assertEquals(false, customEmptyLinkedHashSet.add(10000));
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#remove(Object)}.
     * Сценарий, при котором объект успешно отрабатывает удаление
     * не пустого существующего в дереве объекта.
     */
    @Test
    void removeSuccess_Test() {

        Assert.assertEquals(Arrays.asList(10, 9, 12, 13),
                Arrays.asList(customNotEmptyLinkedHashSet.toArray()));
        Assertions.assertEquals(true, customNotEmptyLinkedHashSet.remove(10));
        Assertions.assertEquals(true, customNotEmptyLinkedHashSet.remove(9));
        Assert.assertEquals(Arrays.asList(12, 13),
                Arrays.asList(customNotEmptyLinkedHashSet.toArray()));
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#removeFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#remove(Object)}.
     * Сценарий, при котором объект не успешно отрабатывает удаление
     * не пустого не существующего в дереве объекта.
     */
    @Test
    void removeFail_Test() {
        Assertions.assertEquals(false, customEmptyLinkedHashSet.remove(10));
        Assertions.assertEquals(false, customNotEmptyLinkedHashSet.remove(99));
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#containsSuccess_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие существующего объекта и
     * возвращает <code>true</code>.
     */
    @Test
    void containsSuccess_Test() {
        Assertions.assertEquals(true, customNotEmptyLinkedHashSet.contains(10));
        Assertions.assertEquals(true, customNotEmptyLinkedHashSet.contains(9));
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#containsFail_Test()}
     * Проверяет проверяет метод {@link CustomLinkedHashSetImpl#contains(Object)}.
     * Сценарий, при котором проверяется наличие не существующего объекта и
     * возвращает <code></code>.
     */
    @Test
    void containsFail_Test() {
        Assertions.assertFalse(customEmptyLinkedHashSet.contains(999));
        Assertions.assertFalse(customNotEmptyLinkedHashSet.contains(9999));

    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#testToStringEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomLinkedHashSetImpl}
     * методом {@link CustomLinkedHashSetImpl#toString()}.
     * Сценарий, при котором пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringEmpty_Test() {
        Assertions.assertEquals("[ ]", customEmptyLinkedHashSet.toString());
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#testToStringNotEmpty_Test()}
     * Проверяет отображение экземпляр объекта {@link CustomLinkedHashSetImpl}
     * методом {@link CustomLinkedHashSetImpl#toString()}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    void testToStringNotEmpty_Test() {
        Assertions.assertEquals(
                "[ 10 9 12 13 ]", customNotEmptyLinkedHashSet.toString());
    }

    /**
     * Метод {@link CustomLinkedHashSetImplTest#toArray_Test} проверяет
     * метод {@link CustomLinkedHashSetImpl#toArray} на
     * создание копии очереди {@link CustomLinkedHashSetImpl}.
     * Сценарий проверяет идентичность созданной копии и исходной очереди по элементно.
     */
    @Test
    void toArray_Test() {
        Assert.assertEquals(Arrays.asList(10, 9, 12, 13),
                Arrays.asList(customNotEmptyLinkedHashSet.toArray()));
        Assert.assertEquals(Arrays.asList(),
                Arrays.asList(customEmptyLinkedHashSet.toArray()));
    }

}