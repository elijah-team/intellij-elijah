// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.Mixin;

public interface DLanguageMixinStatement extends Mixin {

  @Nullable
  DLanguageAssignExpression getAssignExpression();

  @NotNull
  PsiElement getKwMixin();

  @NotNull
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpScolon();

  @Nullable
  String getName();

}
