`[With reference to AY20/21 Sem 2 CS2040S Lecture 3: Searching]`

As an introductory algorithm to all of the following complex concepts and the wonderful world of data structures, binary search is a simple yet elegant algorithm that intrigues our mind into the world of problem-solving.

    BSearch Algorithm
    
    Given: Sorted array, A[0...n-1]
    Return: Index of the key in the array if found, 
            else -1 as not found.

    # note that all variables in here are of type int
    int binarySearch(A, key, n){
        begin = 0
        end = n-1
        while (begin < end) do:
            mid = begin + (end - begin) / 2
            if (key <= A[mid]) then
                end = mid
            else
                begin = mid+1
        return A[begin] == key ? begin : -1

For the beginners of Computer Science like me, I will try to elaborate the purpose of the algorithm in layman's term: 

---

## 1)  Concept:

The idea of binary search simply boils down to finding an item in a specific direction during each step. One important condition to take note of is that the algorithm is only meaningful to execute if you're given a sorted array / an array of items with some natural ordering (ie. flight lift-off timing), else you can just sort the array then check for the presence of the array as you go about your sorting.

---

## 2)  Walkthrough:

So, at first, we start from the middle of the array, if the key we want to find is smaller than the middle item, we simply have to continue our search on the left half, as the items on the right half are always bigger than that at the middle.

Vice versa, if the key we want to find the bigger than the middle item, then we've to search on the right half and ignore the left half.

As such, each time when we run our search in the `while` loop, we are actually narrowing our search zone by half. You can think of it as a converging funnel, where we eventually converge to reach the key we want to find after exhausting the while loop.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/lynx6htuxbt6yljyxk95.png)

## Sample Run
> ###### Given

>|index|0|1|2|3|4|5|6|7|8|
>|---|---|---|---|---|---|---|---|---|---|
>|array|2|2|3|4|5|7|8|9|10|

> ###### binarySearch(key = 7, n = 9)

>>_Initialization Step_
>>
>>begin = 0
>>
>>end = 9 - 1 = 8
>>
>>key = 7

>---

>>_while loop 1_
>>
>>mid = 0 + (8-0)/2 = 4
>>
>>A[mid] = **5**
>>
>>key = 7

>>|index|0|1|2|3|4|5|6|7|8|
>>|---|---|---|---|---|---|---|---|---|---|
>>|array|2|2|3|4|_**5**_|7|8|9|10|
>>||X|X|X|X|mid|✓|✓|✓|✓|
>>||<|<|<|<|mid|>|>|>|>|
>>begin -> mid + 1 -> 5
>>
>>end -> same -> 8

>---

>>_while loop 2_
>>
>>mid = 5 + _**(8-5)/2**_ = 5 + _**1**_ = 6
>>
>>// as we are using int type variable, when there is division, we 
>>
>>// will only take the whole number portion, if there is decimal.
>>
>>A[mid] = **8**
>>
>>key = 7

>>|index|0|1|2|3|4|5|6|7|8|
>>|---|---|---|---|---|---|---|---|---|---|
>>|array|~~2~~|~~2~~|~~3~~|~~4~~|~~5~~|7|_**8**_|9|10|
>>||-|-|-|-|-|✓|mid|X|X|
>>||-|-|-|-|-|<|mid|>|>|
>>begin -> same -> 5
>>
>>end -> mid -> 6

>---

>>_while loop 3_
>>
>>mid = 5 + _**(6-5)/2**_ = 5 + _**0**_ = 5;
>>
>>A[mid] = **7**
>>
>>key = 7

>>|index|0|1|2|3|4|5|6|7|8|
>>|---|---|---|---|---|---|---|---|---|---|
>>|array|~~2~~|~~2~~|~~3~~|~~4~~|~~5~~|_**7**_|8|~~9~~|~~10~~|
>>||-|-|-|-|-|mid|X|-|-|
>>||-|-|-|-|-|mid|>|-|-|
>>begin -> same -> 5
>>
>>end -> mid -> 5

>---

>>_break while loop_
>>
>>since (begin < end) != true
>>
>>return 5 as index since A[begin] == key

---

## 3)  Properties:

    -Functionality:
         i) If the element is in the array, return the index of the element
         ii) If the element not in array, return -1
    -Precondition:
         i) Sorted Array of size n
    -Loop invariant:
         i) A[begin] <= key <= A[end]

## 4)  Performance:

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/tmpew6ct4yp3p3x90t3r.png)

Iteration 1: (end – begin) = n items to search for

Iteration 2: (end – begin) = n/2 items to search for

Iteration 3: (end – begin) = n/4 items to search for

...

Iteration k: (end – begin) = n/(2^k) items to search for

---

In the end, we'll have:

**n/(2^k) = 1**, since there is only 1 item in the array left, which is occupied by the supposed index of the key.

Through algebra transformation, we can obtain:

**k = log(n)**, where k is the number of while loop that is needed to run the BSearch algorithm.

---

For worst runtime,

**T(n) = T(n/2) + O(1)** 

// each time, we halve the size of the array

// O(1), for the calculation of {mid} in the case of integer array

**O(n) = O(1) * O(log n)**

**O(n) = O(log n)**

---

## 5) Guides for reading the lecture slide

In AY20/21 Sem 2 Lecture 3: Searching:

the lecture slide begins with some revisions of runTime analysis and detailed elaboration of Big-O notation. The lecture also touched on the various operations regards to runTime analysis.

For the main bulk, it will be under the introduction of BSearch. The lecture spends a quality amount of time explaining the difficulties of implementing a simple algorithm, where it shows the various bugs that could arise. To find the actual algorithm, you just need to find the latest algorithm shown in the lecture slide. `There is no need to memorize these bugs, they are just here to deliver the message that we need to look out for many details when implementing an algorithm. In the exam, the examiner most likely will test on your ability to sieve out bugs that prevent the algorithm from achieving its intended purpose.`

---

## 6) Questions to ask yourself when augmenting BSearch

    -What is the natural order of the items to be put into the
     array?
    -When do I have to search the left/right? 
    -Think of the loop invariant, how to make sure that the item
     you're looking for is always within the range of search?
    *Remember to handle the situation where the item is not inside
     the array!
    *Be cautious on the difference betwee
