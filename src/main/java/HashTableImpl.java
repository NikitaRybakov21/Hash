import java.util.LinkedList;

public class HashTableImpl<K,V> implements HashTable<K,V> {

    static class Item<K,V> implements Entry<K,V>{
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;

        }

        @Override
        public String toString() {
            return "Item{" + "key=" + key + ", value=" + value + '}';
        }
    }

    private final LinkedList<Item<K, V>>[] data;
    private final Item<K,V> emptyItem;
    private int size;

    public HashTableImpl(int initial) {
        this.data = new LinkedList[initial * 2];

        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }

        emptyItem = new Item<>(null,null);
    }

    private int hashFunc(K key){
        return key.hashCode() % data.length;
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length){
            return false;
        }

        int index = hashFunc(key);

     /* while (data[index] != null){

            if(isKeysEquals(data[index], key)){
                data[index].setValue(value);
            }
            index += getStep(key);
            index %= data.length;

        } */

        data[index].add(new Item<>(key, value));

        size++;

        return true;
    }

    protected int getStep(K key) {
        return 1;
    }

    private boolean isKeysEquals(Item<K,V> item, K key) {
        if(item == emptyItem){
            return false;
        }
        return item.getKey() == null ? key == null
                : item.getKey().equals(key);
    }

    private int indexOf(K key){
        int index = hashFunc(key);

        for (int i = 0; i < data[index].size(); i++) {
            Item<K, V> item = data[index].get(i);

            if(item == null){
                break;
            } else if(isKeysEquals(item, key)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);
        return index == -1 ? null : data[index].get(indexOf(key)).getValue();
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);

        if(index == -1){
            return null;
        }

        Item<K,V> tempItem = data[index].get(indexOf(key));
        data[index].remove(tempItem);

        //data[index] = emptyItem;
        return tempItem.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size != 0;
    }

    @Override
    public void display() {
        System.out.println("--------------------");

        for (int i = 0; i < data.length; i++) {
            System.out.printf("%d = [%s]%n",i,data[i]);
        }

        System.out.println("--------------------");
    }
}
