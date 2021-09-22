public class Main {

    public static void main(String[] args) {
       // HashTable<Product, Integer> hashTable
       //         = new HashTableImpl<>(5);

        HashTable<Product, Integer> hashTable
                = new DoubleHashTable<>(5);

        hashTable.put(new Product(1, "Orange"),150);
        hashTable.put(new Product(77,"Banana"),100);
        hashTable.put(new Product(67,"Carrot"),228);
        hashTable.put(new Product(60,"Lemon"),250);
        hashTable.put(new Product(51,"Milk"),120);
        hashTable.put(new Product(21,"Potato"),67);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        hashTable.remove(new Product(77,"Banana"));
        hashTable.display();
    }
}
