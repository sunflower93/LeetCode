package leetcode.NewYear2022.MyGraph;

import java.util.*;

/**
 * Created by shaobin on 2022/1/30.
 */
public class Solution {
    //单词接龙，开始词到结束次需要此表中几次变化
    public int diff(String beginWord, String endWord) {
        if (beginWord.length() != endWord.length()) return -1;
        int cnt = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) cnt++;
        }
        return cnt == 0 ? 1 : cnt;
    }
    public int ladderLengthHasBug(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        if (beginWord.compareTo(endWord) == 0) return 1;
        int l = wordList.size();
        int[][] graph = new int[l + 1][l + 1];
        graph[l][l] = Integer.MAX_VALUE;
        HashSet<Integer> unarrive = new HashSet<>(l);
        for (int i = 0; i < l; i++) {
            graph[0][i + 1] = diff(beginWord, wordList.get(i)) == 1 ? 1 : Integer.MAX_VALUE;
            graph[i][i] = graph[i][0] = Integer.MAX_VALUE;
            System.out.println("i = " + i + ", graph = " + graph[0][i + 1] + ", beginWord = " + beginWord + ", now = " + wordList.get(i));
            unarrive.add(i);
        }
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                graph[i + 1][j + 1] = diff(wordList.get(i), wordList.get(j)) == 1 ? 1 : Integer.MAX_VALUE;
                graph[j + 1][i + 1] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < l + 1; i++) {
            for (int j = 0; j < l + 1; j++) {
                System.out.print(graph[i][j] + "\t\t\t");
            }
            System.out.println();
        }
        while (!unarrive.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int index = Integer.MAX_VALUE;
            for (Integer j : unarrive) {
                if (graph[0][j + 1] < min) {
                    min = graph[0][j + 1];
                    index = j + 1;
                }
            }
            System.out.println("index = " + index);
            for (int i = 0; i < l + 1; i++) {
                for (int j = 0; j < l + 1; j++) {
                    System.out.print(graph[i][j] + "\t\t\t");
                }
                System.out.println();
            }
            if (index == Integer.MAX_VALUE) return 0;
            if (wordList.get(index - 1).compareTo(endWord) == 0) return min + 1;
            for (int i = 0; i < l + 1; i++) {
                if (graph[index][i] != Integer.MAX_VALUE && graph[0][index] != Integer.MAX_VALUE && graph[0][i] > (graph[0][index] + graph[index][i])) graph[0][i] = graph[0][index] + graph[index][i];
            }
            unarrive.remove(index - 1);
        }
        return l;
    }

    Map<String, Integer> wordId = new HashMap<>();
    List<List<Integer>> edge = new ArrayList<>();
    int nodeNum = 0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }
    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }
    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<>());
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> list = new LinkedList<>();
//        String w1 = "hit";
//        String w2 = "cog";
//        String[] dict = {"hot","dot","dog","lot","log","cog"};
        String w1 = "hot";
        String w2 = "dog";
        String[] dict = {"hot","dog","dot"};
        for (String w : dict) {
            list.add(w);
        }
        System.out.println(solution.ladderLength(w1, w2, list));
    }
}
