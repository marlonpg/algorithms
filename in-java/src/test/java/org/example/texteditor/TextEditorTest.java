package org.example.texteditor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextEditorTest {

    @Test
    void testLeetCodeExampleFlow() {
        TextEditor textEditor = new TextEditor();

        textEditor.addText("leetcode");
        assertEquals(4, textEditor.deleteText(4));

        textEditor.addText("practice");
        assertEquals("etpractice", textEditor.cursorRight(3));
        assertEquals("leet", textEditor.cursorLeft(8));

        assertEquals(4, textEditor.deleteText(10));
        assertEquals("", textEditor.cursorLeft(2));
        assertEquals("practi", textEditor.cursorRight(6));
    }

    @Test
    void testDeleteMoreThanAvailable() {
        TextEditor textEditor = new TextEditor();

        textEditor.addText("abc");
        assertEquals(3, textEditor.deleteText(10));
        assertEquals("", textEditor.cursorLeft(1));
    }

    @Test
    void testCursorMovementBoundaries() {
        TextEditor textEditor = new TextEditor();

        textEditor.addText("hello");
        assertEquals("", textEditor.cursorLeft(10));
        assertEquals("hello", textEditor.cursorRight(10));
    }

    @Test
    void testLeftPreviewLimitedToTenChars() {
        TextEditor textEditor = new TextEditor();

        textEditor.addText("abcdefghijklmnop");
        assertEquals("ghijklmnop", textEditor.cursorRight(1));
    }
}
