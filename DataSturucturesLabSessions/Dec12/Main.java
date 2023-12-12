package Dec12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "aaaaabbbbcccdde";
        System.out.println(solution.compress(str));

    }
}
class Solution {

    /**
     * Counts the number of occurrences of each letter in the given string.
     * @param str: String
     * @return hashMap: Map<Character, Integer>
     */
    public Map<Character,Integer> countLetters(String str){
        HashMap<Character,Integer> map = new HashMap<>();
        char[] letters = str.toCharArray();
        for (char letter : letters) {
            if(map.containsKey(letter)){
                map.put(letter, map.get(letter)+1);
            }else{
                map.put(letter, 1);
            }
        }
        return map;
    }

    /**
     * Prints the given map.
     * @param map: Map<Character, Integer>
     */
    public void printMap(Map<Character,Integer> map){
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /** Compresses the given string using Huffman Coding.
     * @param str: String
     * @return compressedString: String
     */
    public String compress(String str) {
        Map<Character, Integer> map = countLetters(str);
        HuffmanNode root = buildHuffmanTree(map);
        Map<Character, String> codes = getCodes(root);
        StringBuilder compressedString = new StringBuilder();
        for (char letter : str.toCharArray()) {
            compressedString.append(codes.get(letter));
        }
        return compressedString.toString();
    }

    /**
     * Builds a Huffman Tree using the given map.
     * @param map: Map<Character, Integer>
     * @return root: Dec12.HuffmanNode
     */
    public HuffmanNode buildHuffmanTree(Map<Character, Integer> map) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(HuffmanNode::getFrequency)
        );
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            assert right != null;
            HuffmanNode parent = new HuffmanNode('\0', left.getFrequency() + right.getFrequency(), left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    /**
     * Returns a map with letters as keys and their Huffman codes as values.
     * @param root: Dec12.HuffmanNode
     * @return codes: Map<Character, String>
     */
    public Map<Character, String> getCodes(HuffmanNode root) {
        Map<Character, String> codes = new HashMap<>();
        getCodes(root, "", codes);
        return codes;
    }

    public void getCodes(HuffmanNode root, String code, Map<Character, String> codes) {
        if (root == null) {
            return;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            codes.put(root.getValue(), code);
        }
        getCodes(root.getLeft(), code + "0", codes);
        getCodes(root.getRight(), code + "1", codes);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class HuffmanNode {
    private char value;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

}