package dev.fukata.codingwithai

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.ui.content.Content
import java.awt.BorderLayout
import javax.swing.JPanel

class EditorPanel(project: Project) : JPanel(BorderLayout()) {
    private val editor: EditorEx
    private val editorFactory = EditorFactory.getInstance()

    init {
        // プログラムコードのハイライト表示を有効にするエディタコンポーネントを作成
        val document = editorFactory.createDocument("")
        editor = editorFactory.createEditor(document, project) as EditorEx
        editor.isEmbeddedIntoDialogWrapper = true

        // ハイライト表示を有効にする
        // TODO: kotlin 以外にも対応できるようにする
        editor.highlighter =
            EditorHighlighterFactory.getInstance()
                .createEditorHighlighter(project, FileTypeManager.getInstance().getFileTypeByExtension("kt"))

        add(editor.component, BorderLayout.CENTER)
    }

    fun getEditor(): Editor = editor

    fun setDisposer(content: Content) = Disposer.register(content) {
        editorFactory.releaseEditor(editor)
    }
}