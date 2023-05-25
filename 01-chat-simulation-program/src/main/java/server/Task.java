package server;

public class Task {
    Action action;
    String msg;
    int sender;

    public Action getAction() {
        return action;
    }

    public String getMsg() {
        return msg;
    }

    public Task(Action action, String msg, int sender) {
        this.action = action;
        this.msg = msg;
        this.sender = sender;
    }

}
