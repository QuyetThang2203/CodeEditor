package main;

import controller.EditorController;
import model.Document;
import view.EditorView;

public class Editor {
    public static void main(String[] args) {
        Document document = new Document();
        EditorView view = new EditorView();
        EditorController controller = new EditorController(document, view);

        controller.runEditor();
    }
}