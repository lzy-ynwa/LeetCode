package Solution;

import java.util.*;

public class AlienOrder {
    public static void main(String[] args){
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String[] words1 = new String[]{"abc", "ab"};
        System.out.println(alienOrder(words1));
    }

    public static String alienOrder(String[] words) {
        Map<Character, List<Character>> graphMap = new HashMap<>();
        Map<Character, Integer> degreeMap = new HashMap<>();
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length(); j++){
                if(!graphMap.containsKey(words[i].charAt(j))){
                    graphMap.put(words[i].charAt(j), new ArrayList<Character>());
                }
                if(!degreeMap.containsKey(words[i].charAt(j))){
                    degreeMap.put(words[i].charAt(j), 0);
                }
            }
        }

        for(int i = 0; i < words.length - 1; i++){
            int compareLength = Math.min(words[i].length(), words[i + 1].length());
            for(int j = 0; j < compareLength; j++){
                char currentChar = words[i].charAt(j);
                char nextChar = words[i + 1].charAt(j);
                if(currentChar != nextChar){
                    graphMap.get(currentChar).add(nextChar);
                    degreeMap.put(nextChar, degreeMap.get(nextChar) + 1);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: degreeMap.entrySet()){
            if(entry.getValue() == 0){
                queue.offer(entry.getKey());
            }
        }
        StringBuilder sb = new StringBuilder();

        while(queue.peek() != null){
            char c = queue.poll();
            sb.append(c);

            for(char nextChar: graphMap.get(c)){
                int currentDegree = degreeMap.get(nextChar);
                degreeMap.put(nextChar, currentDegree - 1);
                if(currentDegree == 1){
                    queue.offer(nextChar);
                }
            }
        }

        System.out.println("StringBuilder: " + sb.toString());
        System.out.println(sb.capacity());
        System.out.println(graphMap.keySet().size());
        if(sb.toString().length() == graphMap.keySet().size()){
            return sb.toString();
        }else{
            return "";
        }
    }
}
