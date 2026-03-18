package org.example.texteditor;
/**
 * Solving problem https://leetcode.com/problems/design-a-text-editor/
 *
 * Uses a doubly linked list where `cursor` points to the node immediately
 * to the left of the cursor position. `head` and `tail` are sentinel nodes.
 *
 * Layout:  head <-> [chars left of cursor] <-> cursor <-> [chars right of cursor] <-> tail
 */
public class TextEditorV2 {

    private static class Node {
        char ch;
        Node prev, next;

        Node(char ch) {
            this.ch = ch;
        }
    }

    private final Node head;
    private final Node tail;
    private Node cursor;

    public TextEditorV2() {
        head = new Node('\0');
        tail = new Node('\0');
        head.next = tail;
        tail.prev = head;
        cursor = head;
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            Node node = new Node(c);
            node.prev = cursor;
            node.next = cursor.next;
            cursor.next.prev = node;
            cursor.next = node;
            cursor = node;
        }
    }

    public int deleteText(int k) {
        int deleted = 0;
        while (deleted < k && cursor != head) {
            Node toDelete = cursor;
            cursor = cursor.prev;
            cursor.next = toDelete.next;
            toDelete.next.prev = cursor;
            deleted++;
        }
        return deleted;
    }

    public String cursorLeft(int k) {
        int moves = 0;
        while (moves < k && cursor != head) {
            cursor = cursor.prev;
            moves++;
        }
        return getLastTenLeftChars();
    }

    public String cursorRight(int k) {
        int moves = 0;
        while (moves < k && cursor.next != tail) {
            cursor = cursor.next;
            moves++;
        }
        return getLastTenLeftChars();
    }

    public static void main(String[] args) {
        TextEditorV2 textEditor = new TextEditorV2();
        textEditor.addText("mytextexample");
        System.out.println(textEditor.cursorLeft(5));
    }

    private String getLastTenLeftChars() {
        StringBuilder sb = new StringBuilder();
        Node node = cursor;
        int count = 0;
        while (node != head && count < 10) {
            sb.append(node.ch);
            node = node.prev;
            count++;
        }
        return sb.reverse().toString();
    }
}
