package SocialNetwork;

import java.util.ArrayList;

// 친구목록, ID, 부가정보를 담고 있는 데이터객체
public class Person {
    private ArrayList<Integer> friends = new ArrayList<Integer>();
    private int personID;
    private String info;

    public String getInfo() { return info; }
    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<Integer> getFriends() {
        return friends;
    }

    public int getID() { return personID; }
    public void addFriend(int id) { friends.add(id); }

    public Person(int id) {
        this.personID = id;
    }
}
