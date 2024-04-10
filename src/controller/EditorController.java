package controller;

import model.Document;
import view.EditorView;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class EditorController {
    private Document document;
    private EditorView view;

    public EditorController(Document document, EditorView view) {
        this.document = document;
        this.view = view;
    }

    public void updateDocumentContent(String newContent) {
        document.setContent(newContent);
    }

    public void displayDocument() {
        view.displayDocument(document.getContent());
    }

    public void saveDocumentToFile(String filePath) {
        try {
            document.saveToFile(filePath);
            System.out.println("Document saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error saving document to file: " + e.getMessage());
        }
    }

    public void loadDocumentFromFile(String filePath) {
        try {
            document.loadFromFile(filePath);
            System.out.println("Document loaded from file: " + filePath);
        } catch (IOException e) {
            System.out.println("Error loading document from file: " + e.getMessage());
        }
    }

    public void traverseFolder(String folderPath) {
        try {
            List<File> files = getAllFilesInFolder(new File(folderPath));
            System.out.println("Files in folder: " + folderPath);
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error traversing folder: " + e.getMessage());
        }
    }

    private List<File> getAllFilesInFolder(File folder) throws IOException {
        List<File> files = new ArrayList<>();
        if (folder.isDirectory()) {
            File[] fileList = folder.listFiles();
            if (fileList != null) {
                for (File file : fileList) {
                    if (file.isFile()) {
                        files.add(file);
                    } else if (file.isDirectory()) {
                        files.addAll(getAllFilesInFolder(file));
                    }
                }
            }
        }
        return files;
    }

    public void runEditor() {
        boolean running = true;
        while (running) {
            System.out.println("\n===== Text Editor =====");
            System.out.println("1. Create new document");
            System.out.println("2. Open document from file");
            System.out.println("3. Save document to file");
            System.out.println("4. Display document");
            System.out.println("5. Exit");

            String choice = view.getInputFromUser("Enter your choice: ");
            switch (choice) {
                case "1":
                    String content = view.getInputFromUser("Enter the document content: ");
                    updateDocumentContent(content);
                    break;
                case "2":
                    String filePath = view.getInputFromUser("Enter the file path: ");
                    loadDocumentFromFile(filePath);
                    break;
                case "3":
                    filePath = view.getInputFromUser("Enter the file path: ");
                    saveDocumentToFile(filePath);
                    break;
                case "4":
                    displayDocument();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}