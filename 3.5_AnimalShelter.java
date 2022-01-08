package StacksAndQueues;
import java.util.LinkedList;

/** "first in first out". Either the oldest based on arrival time or
 * select preference of dog or cat (receive the oldest animal of that type)
 * cannot select the specific animal they would like
 * Implement operations such as enqueue, dequeueANy, dequeueDog, and dequeueCat
 * You may use the built-in LinkedList data structure
 */
public class AnimalShelter {
    /** Solution
     * Use separate queues for dogs and cats
     * and place them within a wrapper class called AnimalQueue
     * store a timestamp to mark when each anaimal was enqueued
     * When dequeueAny, peak at both the dog and cat queue and return the oldest
     */

    public abstract class Animal {
        private int order;
        protected String name;
        public Animal(String n) {
            name = n;
        }

        public abstract String name();

        public void setOrder(int ord) {
            order = ord;
        }

        public int getOrder() {
            return order;
        }

        public boolean isOlderThan(Animal a) {
            return this.order < a.getOrder();
        }
    }

    class AnimalQueue {
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        private int order = 0;

        public void enqueue (Animal a) {
            /* Order is used as a timestamp, so that we can compare the insertion order */
            a.setOrder(order);
            order++;

            if (a instanceof Dog) {
                dogs.addLast((Dog)a);
            } else if (a instanceof Cat) {
                cats.addLast((Cat) a);
            }
        }

        public Animal dequeueAny() {
            /* Look at tops of dog and cat queues, and pop the queue with the oldest value */
            if (dogs.size() == 0) return dequeueDogs();
            if (cats.size() == 0) return dequeueCats();

            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if (dog.isOlderThan(cat)) {
                return dogs.poll();
            } else {
                return cats.poll();
            }
        }

        public Animal peek() {
            if (dogs.size() == 0) {
                return cats.peek();
            } else if (cats.size() == 0) {
                return dogs.peek();
            }
            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if (dog.isOlderThan(cat)) {
                return dog;
            } else {
                return cat;
            }
        }


        public int size() {
            return dogs.size() + cats.size();
        }

        public Dog dequeueDogs() {
            return dogs.poll();
        }

        public Dog peekDogs() {
            return dogs.peek();
        }

        public Cat dequeueCats() {
            return cats.poll();
        }

        public Cat peekCats() {
            return cats.peek();
        }

    }

    public class Dog extends Animal {
        public Dog(String n) {
            super(n);
        }

        public String name() {
            return "Dog: " + name;
        }
    }

    public class Cat extends Animal {
        public Cat(String n) {
            super(n);
        }

        public String name() {
            return "Cat: " + name;
        }
    }

    public static void main(String[] args) {
        AnimalQueue animals = new AnimalQueue();
        animals.enqueue(new Cat("Callie"));
        animals.enqueue(new Cat("Kiki"));
        animals.enqueue(new Dog("Fido"));
        animals.enqueue(new Dog("Dora"));
        animals.enqueue(new Cat("Kari"));
        animals.enqueue(new Dog("Dexter"));
        animals.enqueue(new Dog("Dobo"));
        animals.enqueue(new Cat("Copa"));

        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueAny().name());
        System.out.println(animals.dequeueAny().name());

        animals.enqueue(new Dog("Dapa"));
        animals.enqueue(new Cat("Kilo"));

        while (animals.size() != 0) {
            System.out.println(animals.dequeueAny().name());
        }
    }

}
