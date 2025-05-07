package com.take_home.tests;

import com.take_home.pages.EditorPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RichTextEditorTest extends BaseTest {

    @Test
    @DisplayName("Case 3 - Rich Text Editor Functionality")
    public void testRichTextEditor() {
        logStep("Opening rich text editor");
        EditorPage editorPage = new EditorPage();
        editorPage.open();

        logStep("Applying bold formatting and typing 'Automation'");
        editorPage.clickBoldButton();
        editorPage.typeText("Automation");
        editorPage.clickBoldButton();

        logStep("Adding space between formatted texts");
        editorPage.typeText(" ");

        logStep("Applying underline formatting and typing 'Test'");
        editorPage.clickUnderlineButton();
        editorPage.typeText("Test");
        editorPage.clickUnderlineButton();

        logStep("Adding final text 'Example'");
        editorPage.typeText(" Example");

        logStep("Validating formatted content");
        String content = editorPage.getEditorContent();
        logValidation("Bold formatting is applied correctly");
        assertTrue(content.contains("<strong>Automation</strong>"), "Content should contain Automation in bold.");

        logValidation("Underline formatting is applied correctly");
        assertTrue(content.contains("<u>Test</u>"), "Content should contain Test underlined.");
    }
}
