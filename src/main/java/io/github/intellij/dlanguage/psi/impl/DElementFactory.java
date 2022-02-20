package io.github.intellij.dlanguage.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import io.github.intellij.dlanguage.DLanguage;
import io.github.intellij.dlanguage.psi.DLanguageAliasDeclaration;
import io.github.intellij.dlanguage.psi.DLanguageImportDeclaration;
import io.github.intellij.dlanguage.psi.DlangFile;
import io.github.intellij.dlanguage.psi.named.DLanguageModuleDeclaration;
import io.github.intellij.dlanguage.psi.named.DlangIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.util.PsiTreeUtil.findChildOfType;

/**
 * Performs creation of element types.
 */
public class DElementFactory {

    /**
     * Takes a name and returns a Psi node of that name, or null.
     */
    @Nullable
    public static DlangIdentifier createDLanguageIdentifierFromText(@NotNull final Project project,
                                                                    @NotNull final String name) {
        final DlangIdentifier element = findChildOfType(
            createExpressionFromText(project, name),
            DlangIdentifier.class
        );
        return element != null && element.getName().equals(name) ? element : null;
    }

    /**
     * Takes an expression in text and returns a Psi tree of that program.
     */
    @Nullable
    private static PsiElement createExpressionFromText(@NotNull final Project project, @NotNull final String name) {
        @NotNull final DlangFile fileFromText = createFileFromText(project, name);

        @Nullable final PsiElement firstChild = fileFromText.getFirstChild();

        // todo: this whole chain could do with being more defensive
        return firstChild != null ? firstChild
            .getFirstChild()
            .getLastChild()
            .getLastChild()
            .getLastChild() // null when creating SingleImportFromText. check: firstChild.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getText()
            .getLastChild() : null;
    }

    /**
     * Create a file containing text.
     */
    @NotNull
    private static DlangFile createFileFromText(@NotNull final Project project,
                                                @NotNull final String text) {
        return (DlangFile) PsiFileFactory.getInstance(project)
            .createFileFromText("A.hs", DLanguage.INSTANCE, text);
    }

    @Nullable
    public static DLanguageModuleDeclaration createDLanguageModuleFromText(@NotNull final Project project,
                                                                           @NotNull final String name) {
        if(StringUtil.isEmptyOrSpaces(name)) {
            return null; // perhaps should throw exception
        }
        return findChildOfType(
            createFileFromText(project, "module " + name + ";"),
            DLanguageModuleDeclaration.class
        );
    }

//    @Nullable // todo: either fix this or get rid of it completely
//    public static DLanguageImportDeclaration createDLanguageSingleImportFromText(@NotNull final Project project,
//                                                                 @NotNull final String name) {
//        if(StringUtil.isEmptyOrSpaces(name)) {
//            return null; // perhaps should throw exception
//        }
//        final PsiElement importExpression = createExpressionFromText(project, "import " + name + ";");
//
//        if(importExpression != null) {
//            final PsiElement element = importExpression.getFirstChild();
//            if (element instanceof DLanguageImportDeclaration) {
//                return (DLanguageImportDeclaration)element;
//            }
//        }
//
//        return null;
//    }

    @Nullable
    public static DLanguageAliasDeclaration createAliasDeclarationFromText(@NotNull final Project project,
                                                                           @NotNull final String text) {
        final PsiFile fileFromText = PsiFileFactory.getInstance(project)
                                                   .createFileFromText("A.d", DLanguage.INSTANCE, text);
        return findChildOfType(fileFromText, DLanguageAliasDeclaration.class);
    }
}

