package org.example.texteditor;
/**
 * Solving problem https://leetcode.com/problems/design-a-text-editor/
 * 
 */
public class TextEditor {

    private final StringBuilder left;
    private final StringBuilder right;

    public TextEditor() {
        this.left = new StringBuilder();
        this.right = new StringBuilder();
    }
    
    public void addText(String text) {
        left.append(text);
    }
    
    public int deleteText(int k) {
        int deleted = Math.min(k, left.length());
        left.delete(left.length() - deleted, left.length());
        return deleted;
    }
    
    public String cursorLeft(int k) {
        int moves = Math.min(k, left.length());
        for (int i = 0; i < moves; i++) {
            right.append(left.charAt(left.length() - 1));
            left.deleteCharAt(left.length() - 1);
        }
        return getLastTenLeftChars();
    }
    // current text is "mytextexample|"
    // if I move cursor right 3 times, I want to get "myt|extexample"
    public String cursorRight(int k) {
        int moves = Math.min(k, right.length());
        for (int i = 0; i < moves; i++) {
            left.append(right.charAt(right.length() - 1));
            right.deleteCharAt(right.length() - 1);
        }
        return getLastTenLeftChars();
    }
    
    private String getLastTenLeftChars() {
        int start = Math.max(0, left.length() - 10);
        return left.substring(start);
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */