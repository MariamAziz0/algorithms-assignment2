package HuffmanCoding;

import java.util.*;

public class HuffmanCodingAlgorithm {

    private Map<String, Long> frequencyMap;
    private Map<String, String> codeWords;
    private long numberBytesOutput;
    private byte numberBitsInLastByte;

    public HuffmanCodingAlgorithm () {
        this.frequencyMap = new HashMap<>();
        this.codeWords = new HashMap<>();
        this.numberBytesOutput = 0;
        this.numberBitsInLastByte = 0;
    }

    public void updateFrequency (String[] sequences) {

        for (String sequence : sequences) {
            Long sequenceFrequency = this.frequencyMap.get(sequence);
            if (sequenceFrequency == null) {
                this.frequencyMap.put(sequence, 1L);
            }
            else {
                this.frequencyMap.put(sequence, sequenceFrequency + 1);
            }
        }

    }

    public Map<String, String> applyHuffman () {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<String, Long> entry : this.frequencyMap.entrySet()) {
            priorityQueue.add(new Node(entry.getValue(), entry.getKey(), null, null));
        }

        if (priorityQueue.size() == 1) {
            this.codeWords.put(priorityQueue.poll().getSequence(), "0");
            return this.codeWords;
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new Node(left.getFrequency() + right.getFrequency(), "", left, right));
        }

        if (priorityQueue.size() == 0) {
            System.out.println("The priority Queue is empty");
            return null;
        }

        Node root = priorityQueue.poll();
        this.updateCodeWords(root, "");
        this.updateNumberOfBytesBits();

        return this.codeWords;
    }

    private void updateCodeWords (Node node, String codeWord) {

        if (node.getLeft() == null && node.getRight() == null && !node.getSequence().equals("")) {
            this.codeWords.put(node.getSequence(), codeWord);
            return;
        }

        updateCodeWords(node.getLeft(), codeWord + "0");
        updateCodeWords(node.getRight(), codeWord + "1");

    }

    private void updateNumberOfBytesBits () {
        long allBits = 0;
        for (Map.Entry<String, String> entry : this.codeWords.entrySet()) {
            allBits += (entry.getValue().length() * (this.frequencyMap.get(entry.getKey())));
        }
        this.numberBytesOutput = (long) Math.ceil(allBits / 8.0);
        this.numberBitsInLastByte = (byte) (allBits - (allBits / 8) * 8);
    }

    public long getNumberBytesOutput() {
        return numberBytesOutput;
    }

    public byte getNumberBitsInLastByte() {
        return numberBitsInLastByte;
    }

    public Map<String, Long> getFrequencyMap() {
        return frequencyMap;
    }

    public Map<String, String> getCodeWords() {
        return codeWords;
    }
}
