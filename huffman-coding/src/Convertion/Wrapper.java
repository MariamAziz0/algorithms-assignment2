package Convertion;

import java.util.*;

public class Wrapper {
    private byte[] byteArray;

    public Wrapper(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Wrapper wrapper = (Wrapper) obj;
        return Arrays.equals(byteArray, wrapper.byteArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(byteArray);
    }

    public static void main(String[] args) {
        byte[] array1 = {1, 2, 3, 4, 5};
        byte[] array2 = {1, 2, 3, 4, 5};
        byte[] array3 = {5, 4, 3, 2, 1};

        Wrapper wrapper1 = new Wrapper(array1);
        Wrapper wrapper2 = new Wrapper(array2);
        Wrapper wrapper3 = new Wrapper(array3);

        // Testing equals method
        System.out.println("wrapper1 equals wrapper2: " + wrapper1.equals(wrapper2)); // true
        System.out.println("wrapper1 equals wrapper3: " + wrapper1.equals(wrapper3)); // false

        // Testing hashCode method
        System.out.println("Hash code for wrapper1: " + wrapper1.hashCode());
        System.out.println("Hash code for wrapper2: " + wrapper2.hashCode());
        System.out.println("Hash code for wrapper3: " + wrapper3.hashCode());

        Map<Wrapper, Integer> hashmap = new HashMap<>();
        hashmap.put(wrapper1, 10);
        hashmap.put(wrapper2, 20);
        hashmap.put(wrapper3, 30);

        System.out.println("wrapper1: " + hashmap.get(wrapper1));
        System.out.println("wrapper2: " + hashmap.get(wrapper2));
        System.out.println("wrapper3: " + hashmap.get(wrapper3));
    }
}
