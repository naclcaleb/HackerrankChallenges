package Anagrams;

import java.util.Scanner;

public class Anagrams {

    private static boolean isAnagram(String a, String b) {
        //Not sure if this is the `best` way to store the frequencies, but it makes access really easy
        java.util.HashMap<Character, Integer> aFrequencies = new java.util.HashMap<>();
        java.util.HashMap<Character, Integer> bFrequencies = new java.util.HashMap<>();

        //First, take care of the case when a is not the same length as b
        if (a.length() != b.length()) return false;

        //Now, let's loop through the strings and get their frequencies
        //(Note that we can use one for loop instead of two because we made sure they were the same length above)
        for (int i = 0; i < a.length(); i++) {
            //Get the two current chars
            char charFromA = Character.toLowerCase( a.charAt(i) );
            char charFromB = Character.toLowerCase( b.charAt(i) );

            //Now update the hashmaps to check their frequencies (if the key doesn't exist in the hashmap, we default to 0)
            aFrequencies.put(  charFromA, aFrequencies.getOrDefault(charFromA, 0) + 1 );
            bFrequencies.put( charFromB, bFrequencies.getOrDefault(charFromB, 0) + 1 );

        }

        //Finally, if the frequencies are equal, return true
        return aFrequencies.equals(bFrequencies);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams.Anagrams" );
    }
}
