package dev.fukata.codingwithai

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindowManager
import javax.swing.JPanel


class SampleAction : AnAction() {
    private val logger = Logger.getInstance(SampleAction::class.java)

    override fun actionPerformed(e: AnActionEvent) {
        logger.info("SampleAction#actionPerformed")

        val project: Project = e.project ?: return
        val editor: Editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText

        val toolWindowManager = ToolWindowManager.getInstance(project)
        val toolWindow = toolWindowManager.getToolWindow("dev.fukata.codingwithai.SampleToolWindow")
        if (toolWindow != null) {
            logger.info("toolWindow.contentManager.contentCount: ${toolWindow.contentManager.contentCount}")
            val editorPanel = toolWindow.contentManager.getContent(0)?.component as? EditorPanel
            val document: Document = editorPanel?.getEditor()?.document ?: return
            logger.info("document.text: ${document.text}")
            document.setText(selectedText ?: "")
        }
    }

    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        e.presentation.isEnabled = editor != null && editor.selectionModel.hasSelection()
    }
}