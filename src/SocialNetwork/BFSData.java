package SocialNetwork;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// 너비우선탐색하기 위해 PathNode를 감싸는 객체 
public class BFSData {
    public Queue<PathNode> toVisit = new LinkedList<PathNode>(); // 앞으로 방문할 노드 리스트
    public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>(); // 지금까지 방문한 노드 리스트

    public BFSData(Person root) {
        PathNode sourcePath = new PathNode(root, null);
        toVisit.add(sourcePath);
        visited.put(root.getID(), sourcePath);
    }

    public boolean isFinished() {
        return toVisit.isEmpty();
    }
}
