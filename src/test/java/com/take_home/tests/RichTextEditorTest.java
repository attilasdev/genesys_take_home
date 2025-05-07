package com.take_home.tests;

import com.take_home.pages.EditorPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RichTextEditorTest {
    
    @Test
    public void testRichTextEditor() throws InterruptedException {
        EditorPage editorPage = new EditorPage();
        editorPage.open();

        editorPage.clickBoldButton();
        editorPage.typeText("Automation");
        editorPage.clickBoldButton();

        editorPage.typeText(" ");

        editorPage.clickUnderlineButton();
        editorPage.typeText("Test");
        editorPage.clickUnderlineButton();

        editorPage.typeText(" Example");

        String content = editorPage.getEditorContent();
        assertTrue(content.contains("<strong>Automation</strong>"), "Content should contain Automation in bold.");
        assertTrue(content.contains("<u>Test</u>"), "Content should contain Test underlined.");
    }
}
