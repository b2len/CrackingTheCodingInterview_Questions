package OOD;

/** Three layers for a call center: respondent, manager, director.
 *  An incoming call first go to respondent then escalate level as no one pick them up
 *  Design the classes and data structures for this problem
 *  Implement a method dispatchCall() which assigns a call to the first available employee
  */

public class CallCenter {
    /** An employee class to hold common things such as address, anme, job, rank etc,
     * make it an abstract class and extend to respondent, manager, director etc,
     * one CallHandler class which would route the calls to the correct person
     */

    private Call currentCall = null;
    protected Rank rank;
    private CallHandler callHandler;

    public Employee(CallHandler handler) {
        callHandler = handler;
    }

    /* Start the conversation */
    public void receiveCall(Call call) {
        currentCall = call;
    }

    /* the issue is resolved, finish the call */
    public void callCompleted() {
        if (currentCall != null) {
            /* Disconnect the call. */
            currentCall.disconnect();

            /* Free the employee */
            currentCall = null;
        }

        /* Check if there is a call waiting in queue */
        assignNewCall();
    }

    /*
     * The issue has not been resolved. Escalate the call, and assign a new call
     * to the employee.
     */
    public void escalateAndReassign() {
        if (currentCall != null) {
            /* escalate call */
            currentCall.incrementRank();
            callHandler.dispatchCall(currentCall);

            /* free the employee */
            currentCall = null;
        }

        /* assign a new call */
        assignNewCall();
    }

    /* Assign a new call to an employee, if the employee is free. */
    public boolean assignNewCall() {
        if (!isFree()) {
            return false;
        }
        return callHandler.assignCall(this);
    }

    /* Returns whether or not the employee is free. */
    public boolean isFree() {
        return currentCall == null;
    }

    public Rank getRank() {
        return rank;
    }

    class Director extends Employee {
        public Director(CallHandler callHandler) {
            super(callHandler);
            rank = Rank.Director;
        }
    }

    class Manager extends Employee {
        public Manager(CallHandler callHandler) {
            super(callHandler);
            rank = Rank.Manager;
        }
    }

    class Respondent extends Employee {
        public Respondent(CallHandler callHandler) {
            super(callHandler);
            rank = Rank.Responder;
        }
    }

    public enum Rank {
        Responder (0),
        Manager (1),
        Director (2);

        private int value;

        private Rank(int v) {
            value = v;
        }

        public int getValue() {
            return value;
        }
    }

    public class Caller {
        private String name;
        private int userId;
        public Caller(int id, String nm) {
            name = nm;
            userId = id;
        }
    }


}
