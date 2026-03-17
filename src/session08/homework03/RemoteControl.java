package session08.homework03;

import java.util.*;

class RemoteControl {
    private Map<Integer, Command> slots = new HashMap<>();
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        slots.put(slot, command);
        System.out.println("Đã gán " + command.getClass().getSimpleName() + " cho nút " + slot);
    }

    public void pressButton(int slot) {
        Command command = slots.get(slot);
        if (command != null) {
            command.execute();
            history.push(command);
        } else {
            System.out.println("Nút " + slot + " chưa được gán lệnh.");
        }
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command last = history.pop();
            System.out.print("Undo: ");
            last.undo();
        } else {
            System.out.println("Không có lệnh nào để Undo.");
        }
    }
}

