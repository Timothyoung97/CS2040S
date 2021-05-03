CS2040S Guide 02: Peak Finding

`With reference to AY20/21 Sem 2 CS2040S Lecture 4: Peak Finding`

This lecture serves as a follow-up to the logic used in the idea of binary search, _Reduce-and-Conquer_, which is helpful to students in terms of visualizing how to augment certain aspects of the BSearch algorithm to fit into our purpose.

    PeakFind Algorithm
    
    Given:  Some Array[0...n-1]
    Return: An index in A that represents the local maximum such 
            that A[i-1] <= A[i] >= A[i+1]
    Assume: A[-1] = A[n] = -MAX_INT

    FindPeak(A, n){
        if (A[n/2 + 1] > A[n/2]) 
            # search for peak in the right half
            FindPeak(A[n/2 + 1 ... n], n/2)
        else if (A[n/2 - 1] > A[n/2])
            # search for peak in the left half
            FindPeak(A[1 ... n/2 - 1], n/2)
        else
            # A[n/2] is a peak
            return n/2
    }

While you're reading up the lecture note, there may be certain proofings that are hard to comprehend at first. Just take a step back and come back to them later, they wouldn't cause that much of an issue during your problem set.

Meanwhile, here are some tips to help you understand the definition of a peak:
- a peak can exist at the edge of the dataset since we assume that positions beyond the dataset are negative infinity.
- As long as the peak satisfies the following condition:
  - A[i-1] <= A[i] >= A[i+1]

---

### 1) Concept

The motivation of peak finding generates from the intention of finding a global peak, given an array of data, to meet certain goals (ie. finding the optimal way to generate more money, the optimal way to save more energy, the optimal amount of fuels needed to leave the planet, etc). However, given a large enough set of data, it would often be very time-consuming to find the global peak when there are local peaks that can do the same job as the global peak. Hence when we reduce our scope to find only 1 local peak, we are rewarded with a decrease amount of time needed using the idea of reduce-and-conquer.

---

### 2) Walkthrough

Similar to the BSearch algorithm, we begin our search from the middle of the dataset:
- if the middle is strictly bigger than the next item on the right, we reduce the search zone to only the right half.
- if the middle is not strictly bigger than the next item on the right, we then check to see if the middle is strictly bigger than the next item on its left. If true, then we reduce the search zone to only the left half of the dataset.
- if the above 2 conditions both fails, this means that we already found the peak! Hooray!

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/jod1p1azc3eminqkc1bh.jpg)

### Sample Run
>###### Given
>|Index|0|1|2|3|4|5|6|7|8|9|10|11|
>|---|---|---|---|---|---|---|---|---|---|---|---|---|
>|Array|2|4|9|2|5|6|23|4|6|8|17|5|
>###### FindPeak(Array, n = 12)
>>_Loop 1_
>>
>>mid = begin + (end-begin) / 2 = 0 + (11-0) / 2 = 5 
>>
>>A[mid] = _**6**_
>>|Index|0|1|2|3|4|5|6|7|8|9|10|11|
>>|---|---|---|---|---|---|---|---|---|---|---|---|---|
>>|Array|2|4|9|2|5|_**6**_|23|4|6|8|17|5|
>>||||||<|mid|<||||||
>>|next search zone|X|X|X|X|X|X|✓|✓|✓|✓|✓|✓|
>>
>>FindPeak(A[6,11], n = 6) // we proceed to the left half
>---
>>_Loop 2_
>>
>>mid = begin + (end-begin) / 2 = 6 + (11-6) / 2 = 8
>>
>>A[mid] = _**6**_
>>|Index|0|1|2|3|4|5|6|7|8|9|10|11|
>>|---|---|---|---|---|---|---|---|---|---|---|---|---|
>>|Array|~~2~~|~~4~~|~~9~~|~~2~~|~~5~~|~~6~~|23|4|6|8|17|5|
>>||-|-|-|-|-|-||<|mid|<|||
>>|next search zone|-|-|-|-|-|-|X|X|X|✓|✓|✓|
>>
>>FindPeak(A[9,11], n = 3) // we continue our search in the left half
>---
>>_Loop 3_
>>
>>mid = begin + (end-begin) / 2 = 9 + (11-9) / 2 = 10
>>
>>A[mid] = _**17**_
>>|Index|0|1|2|3|4|5|6|7|8|9|10|11|
>>|---|---|---|---|---|---|---|---|---|---|---|---|---|
>>|Array|~~2~~|~~4~~|~~9~~|~~2~~|~~5~~|~~6~~|~~23~~|~~4~~|~~6~~|8|17|5|
>>||-|-|-|-|-|-|-|-|-|<=|mid|>=|
>>
>>Hooray! We found the peak at index 10!

---

### 3) Properties

    -Key Invariant (Why the Algorithm works?):
        i) If we recurse in the right half, then there exists a 
           peak in the right half. (Proved in lecture using 
           induction and contradiction. Good to know)
       ii) There exists a peak in the range [begin, end]
      iii) Every peak in [begin, end] is a peak in [1, n]
       iv) If we recurse in the right half, then every peak in the 
           right half is a peak in the array.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/3vzfjh00lapuo73caksd.png)

`Note that when you're reading the lecture slide, there is a change of value from 13 to 60. This is used to illustrate that the recursion would not recurse to the right half if 45 is a peak in the new array but not the old array.`

---

### 4) Performance

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/6mvxi2bw1ono1e50pagg.PNG)

`Note that this unrolling of the recursion to determine the runTime performance is especially important. Many exam questions (those questions that are near the front of the paper) would test your ability to unroll the recursion and to analyze the worst runTime performance of a given algorithm. Questions may get tricky as they introduce various loops and nested loops into a single method. Just make sure that you're focused on finding the worst case that would result in the worst runTime.`

---

### 5) Special Case

There are certain examples of datasets that would eventually result in an ineffective runTime.

>For example
>|Index|0|1|2|3|4|5|6|7|8|9|10|11|
>|---|---|---|---|---|---|---|---|---|---|---|---|---|
>|Array|7|7|7|7|7|7|7|7|7|8|7|7|

>Since the algorithm starts from the middle, we would then have to recurse on both sides of the array in order to reach peak 8. However, this will eventually result in a runTime of O(n) where we end up searching for a peak in an entire array, which is no different from using a naive method.

![Alt Text](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/113rjmu573vo5fc973rx.PNG)

---

### 6) A bigger picture

Do remember that the peak finding algorithm is rooted based on the idea of binary search, where we divide the amount of work we're required to do in each step of execution. 

From an aspect of dynamic programming, we could eventually use this 1-dimensional peak finding algorithm and extend it to solve 2-dimensional peak finding or even 3-dimensional peak finding. (For detailed elaboration, you can refer to AY19/20 Sem 2 Lecture: Searching)

---

### 7) Questions to ask yourself when augmenting PeakFind

    - What is defined as a "peak" in the given dataset? 
    - How is it an optimal solution as compared to the other datasets? 
    - Under what conditions do I proceed with the left/right half?
