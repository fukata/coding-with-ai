package dev.fukata.codingwithai

import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.editor.highlighter.EditorHighlighterFactory
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory


class SampleToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
//        // プログラムコードのハイライト表示を有効にするエディタコンポーネントを作成
//        val editorFactory = EditorFactory.getInstance()
//        val document = editorFactory.createDocument("")
//        val editor = editorFactory.createEditor(document, project) as EditorEx
//        editor.isEmbeddedIntoDialogWrapper = true
//
//        // ハイライト表示を有効にする
//        editor.highlighter =
//            EditorHighlighterFactory.getInstance()
//                .createEditorHighlighter(project, FileTypeManager.getInstance().getStdFileType("kt"))
//
        val editorPanel = EditorPanel(project)

        // エディタコンポーネントをToolWindowに追加
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(editorPanel, "", false)
        toolWindow.contentManager.addContent(content)

        // エディタコンポーネントを適切に破棄するためのDisposerを設定
        editorPanel.setDisposer(content)
//        Disposer.register(content) {
//            editorFactory.releaseEditor(editor)
//        }
    }
}