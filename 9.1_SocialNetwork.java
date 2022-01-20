package SystemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SocialNetwork {
    public static LinkedList<Person> findPathBFS(HashMap<Integer, Person> people, int source, int destination) {
        Queue<PathNode> toVisit = new LinkedList<PathNode>();
        HashSet<Integer> visited = new HashSet<Integer>();
        toVisit.add(new PathNode(people.get(source), null));
        visited.add(source);
        while (!toVisit.isEmpty()) {
            PathNode node = toVisit.poll();
            Person person = node.getPerson();
            if (person.getID() == destination) {
                return node.collapse(false);
            }

            /* Search friends. */
            ArrayList<Integer> friends = person.getFriends();
            for (int friendId : friends) {
                if (!visited.contains(friendId)) {
                    visited.add(friendId);
                    Person friend = people.get(friendId);
                    toVisit.add(new PathNode(friend, node));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
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
        LinkedList<Person> path1 = findPathBFS(people, i, j);
        Tester.printPeople(path1);
    }

    public class Server {
        HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
        HashMap<Integer, Integer> personToMachineMap = new HashMap<Integer, Integer>();

        public Machine getMachineWithId(int machineID) {
            return machines.get(machineID);
        }

        public int getMachineIDForUser(int personID) {
            Integer machineID = personToMachineMap.get(personID);
            return machineID == null ? -1 : machineID;
        }

        public Person getPersonWithID(int personID) {
            Integer machineID = personToMachineMap.get(personID);
            if (machineID == null) {
                return null;
            }
            Machine machine = getMachineWithId(machineID);
            if (machine == null) {
                return null;
            }
            return machine.getPersonWithID(personID);
        }

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

        public class User {
            private String name;
            public String getName() { return name; }
            public void setName(String name) {	this.name = name; }
            public long getID() { return ID; }
            public void setID(long iD) { ID = iD; }
            private long ID;
            public User(String name, long iD) {
                this.name = name;
                ID = iD;
            }
            public User getUser() { return this; }
            public static User addUser(String name, long iD){
                return new User(name, iD);
            }
        }

    }

    public class Machine {
        public HashMap<Integer, Person> persons = new HashMap<Integer, Person>();
        public int machineID;

        public Person getPersonWithID(int personID) {
            return persons.get(personID);
        }
    }

    public class PathNode {
        private Person person = null;
        private PathNode previousNode = null;
        public PathNode(Person p, PathNode previous) {
            person = p;
            previousNode = previous;
        }

        public Person getPerson() {
            return person;
        }

        public LinkedList<Person> collapse(boolean startsWithRoot) {
            LinkedList<Person> path = new LinkedList<Person>();
            PathNode node = this;
            while (node != null) {
                if (startsWithRoot) {
                    path.addLast(node.person);
                } else {
                    path.addFirst(node.person);
                }
                node = node.previousNode;
            }
            return path;
        }
    }

    public class BFSData {
        public Queue<PathNode> toVisit = new LinkedList<PathNode>();
        public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>();

        public BFSData(Person root) {
            PathNode sourcePath = new PathNode(root, null);
            toVisit.add(sourcePath);
            visited.put(root.getID(), sourcePath);
        }

        public boolean isFinished() {
            return toVisit.isEmpty();
        }
    }

}

