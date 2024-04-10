package view;

import java.util.Scanner;

public class EditorView {
    private Scanner scanner;

    public EditorView() {
        scanner = new Scanner(System.in);
    }

    public void displayDocument(String content) {
        System.out.println("Document content:");
        System.out.println(content);
    }

    public String getInputFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void setEventListener(Runnable eventHandler) {
        eventHandler.run();
    }
}