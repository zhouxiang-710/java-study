package com.base.Map;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 20:19 2020/7/23
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
public interface Map<K,V> {
    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}