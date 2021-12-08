package SocialNetwork;

import java.util.HashMap;

// 데이터를 저장할 서버기계
public class Machine {
    public HashMap<Integer, Person> persons = new HashMap<Integer, Person>();
    public int machineID;

    public Person getPersonWithID(int personID) {
        return persons.get(personID);
    }
}
