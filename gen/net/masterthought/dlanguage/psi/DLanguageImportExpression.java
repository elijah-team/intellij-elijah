// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageImportExpression extends PsiElement {

  @NotNull
  DLanguageAssignExpression getAssignExpression();

  @NotNull
  PsiElement getImport();

  @NotNull
  PsiElement getLParen();

  @NotNull
  PsiElement getRParen();

}
