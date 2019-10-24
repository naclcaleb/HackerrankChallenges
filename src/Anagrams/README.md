Hackerrank sent me one of their daily challenges today. I've been going through some of their Java challenges recently, so I was recommended the "Java Anagrams" challenge. 

You can go to the challenge yourself here: https://www.hackerrank.com/challenges/java-anagrams/problem

I recommend reading the problem first and then finishing this article.

So, there are a few ways to approach this problem. 

One of the shortest solutions I've seen consists of:
```
static void isAnagram(String a, String b) {
    Array.sort(a);
    Array.sort(b);
    return a.equals(b);
}
```

While that works and is quick to type, it's not the most efficient way to do this: it has `n*log(n)` runtime.

Here's how I approached it. First things first, there's a really quick way to know whether two words are NOT anagrams: check their lengths. If they don't have the same length, there's no way they can be anagrams!

So let's start our code off like this:
```
if (a.length() != b.length()) return false
```

(Java String.length() has a runtime complexity of O(1))

But what if they are the same length? Well, in that case, we need to have a way to store all the frequencies for each character in the strings. My preferred way to do this is via a HashMap:

```
HashMap<Character, Integer> aFrequencies = new HashMap<>();
HashMap<Character, Integer> bFrequencies = new HashMap<>();
```

Now, we need to collect the frequencies of each letter in each string. This means looping through each letter in each string.

Which means we have to use two for loops right? 
Actually, a second benefit to our above optimization (checking the length of the strings) is that if the code has got to this point, we know the strings have equal length. Therefore, we really only need to use one for loop:

```
for (int i = 0; i < a.length(); i++) {
    //Get the two current chars
    char charFromA = Character.toLowerCase( a.charAt(i) );
    char charFromB = Character.toLowerCase( b.charAt(i) );
    
    //Now update the hashmaps to check their frequencies (if the key doesn't exist in the hashmap, we default to 0)
    aFrequencies.put(  charFromA, aFrequencies.getOrDefault(charFromA, 0) + 1 );
    bFrequencies.put( charFromB, bFrequencies.getOrDefault(charFromB, 0) + 1 );
}
```

In our for loop, we start by getting the characters at their positions in the Strings. You may notice that I run `.toLowerCase` individually on the characters _inside_ the loop, rather than running it on the entire strings outside the loop.

I could be wrong, but I think this way is a bit more efficient, for two reasons:
1. I imagine that `String.toLowerCase` has to use at least one for loop to make all the characters lowercase. It's best to keep the number of operations to a minimum, so I just put it in our already existing loop.
2. Java Strings are immutable, so running `toLowerCase` will have to create a new String, making it less memory efficient.

After getting the characters, we simply increment their frequencies in their HashMaps (we use `getOrDefault` to make sure it still works if the key doesn't exist).

We're pretty much done!
Now all we have to do is check whether or not the frequencies are equal: 
```
return aFrequencies.equals(bFrequencies);
```


Now, this is not the most efficient solution. Here's one from the Hackerrank forums:
https://www.hackerrank.com/challenges/java-anagrams/forum

That solution is better because it requires only one HashMap.

However, my solution is still pretty close, and I believe the runtime is something like `2n` (It would be great if someone could verify that for me; I'm not super knowledgable when it comes to calculating runtimes, or what the runtimes are for some of Java's functions).

I hope you enjoyed reading this!