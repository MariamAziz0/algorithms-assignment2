package HuffmanCoding;

public class Node implements Comparable<Node> {

    private long frequency;
    private String sequence;
    private Node left;
    private Node right;

    public Node(long frequency, String sequence, Node left, Node right) {
        this.frequency = frequency;
        this.sequence = sequence;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.frequency, o.getFrequency());
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
