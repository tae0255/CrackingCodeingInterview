package SocialNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class QuestionB {

    // 경로를 합친다
    public static LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connection) {
        PathNode end1 = bfs1.visited.get(connection); // end1 -> source
        PathNode end2 = bfs2.visited.get(connection); // end2 -> dest
        LinkedList<Person> pathOne = end1.collapse(false); // forward: source -> connection
        LinkedList<Person> pathTwo = end2.collapse(true); // reverse: connection -> dest
        pathTwo.removeFirst(); // remove connection
        pathOne.addAll(pathTwo); // add second path
        return pathOne;
    }

    // 양 방향에서 한 단계씩 탐색하고, 경로가 만나는지 확인한다
    public static Person searchLevel(HashMap<Integer, Person> people, BFSData primary, BFSData secondary) {
        // 한 번에 한 단계만 탐색하려면, 이전 단계에서 추가한 노드 개수를 기억하고
        // 큐에서 해당되는 노드만 꺼내서 탐색하면 된다
        int count = primary.toVisit.size();
        for (int i = 0; i < count; i++) {
            // 첫번째 노드를 꺼낸다
            PathNode pathNode = primary.toVisit.poll();
            int personId = pathNode.getPerson().getID();

            // 역방향 탐색에서 방문한 노드인지 확인
            //===========================================
            if (secondary.visited.containsKey(personId)) {
                return pathNode.getPerson();
            }
            //===========================================

            // 너비우선탐색
            Person person = pathNode.getPerson();
            ArrayList<Integer> friends = person.getFriends();
            for (int friendId : friends) {
                if (!primary.visited.containsKey(friendId)) {
                    Person friend = people.get(friendId);
                    PathNode next = new PathNode(friend, pathNode);
                    primary.visited.put(friendId, next);
                    primary.toVisit.add(next);
                }
            }
        }
        return null;
    }

    // 양방향 탐색 본체
    public static LinkedList<Person> findPathBiBFS(HashMap<Integer, Person> people, int source, int destination) {
        BFSData sourceData = new BFSData(people.get(source));
        BFSData destData = new BFSData(people.get(destination));

        while (!sourceData.isFinished() && !destData.isFinished()) {
            // 정방향
            Person collision = searchLevel(people, sourceData, destData);
            if (collision != null) {
                return mergePaths(sourceData, destData, collision.getID());
            }

            // 역방향
            collision = searchLevel(people, destData, sourceData);
            if (collision != null) {
                return mergePaths(sourceData, destData, collision.getID());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("===============QUESTION B RESULT===============");

        int nPeople = 11;
        HashMap<Integer, Person> people = new HashMap<Integer, Person>();
        for (int i = 0; i < nPeople; i++) {
            Person p = new Person(i);
            people.put(i, p);
        }

        int[][] edges = {{1, 4}, {1, 2}, {1, 3}, {3, 2}, {4, 6}, {3, 7}, {6, 9}, {9, 10}, {5, 10}, {2, 5}, {3, 7}};

        for (int[] edge : edges) {
            Person source = people.get(edge[0]);
            source.addFriend(edge[1]);

            Person destination = people.get(edge[1]);
            destination.addFriend(edge[0]);
        }

        int i = 1;
        int j = 10;
        LinkedList<Person> path1 = findPathBiBFS(people, i, j);
        Tester.printPeople(path1);
    }
}
