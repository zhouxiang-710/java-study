package com.base.Map;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 20:21 2020/7/23
 * @ Description：TODO 模拟HashMap数组+链表的实现,没有实现红黑树，java8的HashMap在java7的基础上增加了红黑树这种数据结构
 * @ Modified By：
 * @Version: $version$
 */
public class HashMap<K, V> implements Map<K, V> {
    Entry<K, V> table[] = null;
    int size = 0;
    public HashMap() {
        table = new Entry[16];
    }
    /****
     *  *  1、hash》hashcode》取模
     *  *  2、拿到下标值 对应Entry数组去找到当前下标值
     *  *  3、如果为空 直接存储》数组
     *  *  4、如果不为空 用链表
     *  * @param k
     *  * @param v
     *  * @return
     *  */
    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            table[index] = new Entry<>(k, v, index, null);
            size++;
        } else {
            table[index] = new Entry<>(k, v, index, entry);
        }
        return null;
    }
    private int hash(K k) {
        int i = k.hashCode() % 16;
        //return i>=0?i:-i;
         return Math.abs(i);
    }
    /***
     * * 1、k 去hash
     * * 2、数组下标
     * * 3、当前下标 和我查询的下标值是否相等、
     * * 4、相等》直接放回 说明查询到
     * * 5、不相等》判断当前next是否为空
     * * 6、为空直接返回null 相等不为空 直接返回
     * * @param k
     * * @return     */
    @Override
    public V get(K k)
    {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            return null;
        }
        return findValue(k, entry);
    }
    private V findValue(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            if (entry.next != null) {
                return findValue(k, entry.next);
            }
        }
            return null;
    }
    @Override
    public int size() {
        return size;
    }
    class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        int hash;
        Entry<K, V> next;
        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }
        @Override
        public K getKey() {
            return k;
        }
        @Override
        public V getValue() {
            return v;
        }
    }
}



