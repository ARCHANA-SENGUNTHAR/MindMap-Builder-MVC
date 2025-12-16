package utils;

import java.util.*;

public class PathFindingHelper {
    // BFS returns list of node ids from source...target
    public static List<Integer> bfsFindPath(int src, int target, Map<Integer, List<Integer>> adj) {
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> parent = new HashMap<>();
        q.add(src);
        parent.put(src, null);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == target)
                break;
            for (int v : adj.getOrDefault(u, List.of())) {
                if (!parent.containsKey(v)) {
                    parent.put(v, u);
                    q.add(v);
                }
            }
        }
        if (!parent.containsKey(target))
            return null;
        LinkedList<Integer> path = new LinkedList<>();
        Integer cur = target;
        while (cur != null) {
            path.addFirst(cur);
            cur = parent.get(cur);
        }
        return path;
    }
}