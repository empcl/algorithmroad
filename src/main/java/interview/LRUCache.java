package interview;

import java.util.HashMap;

/**
 * Module Desc: 实现LRU缓存算法
 * User: empcl
 * DateTime: 2019/7/31 16:59
 */
class Entry {
    Entry prev;
    Entry next;
    Object key;
    Object value;

    public Entry (Object key) {
        this.key = key;
    }
}

public class LRUCache {
    private int currentSize;
    private int cacheSize;
    private HashMap<Object,Entry> nodes;
    private Entry last;
    private Entry first;

    public LRUCache(int capcity) {
        this.cacheSize = capcity;
        this.currentSize = 0;
        this.nodes = new HashMap<Object,Entry>();
    }

    public Entry get(Object key) {
        Entry node = nodes.get(key);
        if (node != null) { // 将当前节点转移到头结点位置
            moveToHead(node);
            return node;
        }
        return null;
    }

    // 过程：
    // 1.查看所要插入的key值是否在HashMap中。
    // 2.如果存在的话，将key对应的value替换为入参value，并将当前节点作为链表的头结点。
    // 3.如果不存在的话，首先判断，缓存是否满了。
    // 4.如果缓存满了的话，首先，从链表中移除最后一个节点，并从hashMap中移除最后一个节点对应的key、value。
    //   在链表的头结点中插入这个值对应的节点，并且将当前的值添加到hashMap中。
    public void put(Object key,Object value) {
        Entry node = nodes.get(key);
        if (node == null) {
            if (currentSize >= cacheSize) {
                nodes.remove(last.key);
                removeLast();
            } else {
                currentSize++;
            }
            node = new Entry(key);
        }
        node.value = value;
        moveToHead(node);
        nodes.put(key,node);
    }

    private void moveToHead(Entry node) {
        if (first == node)
            return;
        if (node.prev != null)
            node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
        if (node == last)
            last = last.prev;
        if (first != null) {
            node.next = first;
            first.prev = node;
        }
        first = node;
        first.prev = null;
        if (last == null)
            last = first;
    }

    private void removeLast() {
        if (last != null) {
            if (last.prev != null)
                last.prev.next = null;
            else
                first = null;
            last = last.prev;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,"tom");
        lruCache.put(2,"jack");
        lruCache.put(3,"tim");
        lruCache.put(4,"rose");
        System.out.println(lruCache);
    }
}