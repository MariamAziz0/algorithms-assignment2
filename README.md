# algorithms-assignment2

## Part 1 - Weighted Activity Selection
The Weighted Activity Selection problem entails the selection of a subset of activities with the maximum total weight, while ensuring that no two activities selected overlap. A dynamic programming solution with a time complexity of O(n lg n) is proposed for this problem.

The approach commences with the sorting of activities based on their end times in non-decreasing order. Subsequently, a dynamic programming table is initialized to record the maximum total weight at each step. The algorithm iterates through the sorted activities, calculating the maximum weight for each activity by considering its compatibility with previously selected activities. 

This dynamic programming approach provides an efficient solution to the Weighted Activity Selection problem, offering a dependable method for selecting a subset of mutually compatible activities with high weight.

## Part 2 - Huffman's Algorithm Implementation
The task at hand involves implementing Huffman's algorithm to enable the compression and decompression of arbitrary files. The algorithm, as discussed in class, follows a two-step process: collecting statistics from the input file and then applying the compression algorithm. This ensures that the program can efficiently represent the codewords in the compressed file, facilitating the decompression process.

In this implementation, the program is designed to handle more than one byte as the basic unit. Instead of solely collecting frequencies and generating codewords for single bytes, it accommodates a user-defined basic unit size, allowing for the consideration of n bytes, where n is an integer. This flexibility enhances the adaptability of the algorithm to different file formats and data structures.

By incorporating these features, the implemented Huffman's algorithm serves as a versatile tool for compressing and decompressing arbitrary files, with the added capability of adjusting to different byte units for more efficient data representation.
