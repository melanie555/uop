package org.example.controller.state;

import org.example.controller.AppAction;
import org.example.view.menu.CommandActionListeners;

import java.util.LinkedList;

public class UndoMachine {
    private UndoRedoState undoRedoState;
    private CommandActionListeners undo;
    private CommandActionListeners redo;

    public UndoMachine() {
        LinkedList<AppAction> undoList = new LinkedList<>();
        LinkedList<AppAction> redoList = new LinkedList<>();
        undoRedoState = new StateDisableUndoDisableRedo(undoList, redoList);
    }

    public void executeRedo() {
        undoRedoState = undoRedoState.redo();
    }

    public void executeUndo() {
        undoRedoState = undoRedoState.undo();
    }

    public boolean isEnableUndo() {
        return !undoRedoState.getUndoActivityList().isEmpty();
    }


    public boolean isEnableRedo() {
        return !undoRedoState.getRedoActivityList().isEmpty();
    }

    public void add(AppAction action) {
        undoRedoState.clearHistory();
        undoRedoState.addAction(action);
        undoRedoState = new StateEnableUndoDisableRedo(undoRedoState.getUndoActivityList(), undoRedoState.getRedoActivityList());
    }
    public void updateButtons() {
        undo.setEnabled(isEnableUndo());
        redo.setEnabled(isEnableRedo());

    }

    public void setUndo(CommandActionListeners undo) {
        this.undo = undo;
    }

    public void setRedo(CommandActionListeners redo) {
        this.redo = redo;
    }
}
