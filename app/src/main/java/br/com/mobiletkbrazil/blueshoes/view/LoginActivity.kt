package br.com.mobiletkbrazil.blueshoes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.blankj.utilcode.util.ScreenUtils
import kotlinx.android.synthetic.main.content_login.*
import br.com.mobiletkbrazil.blueshoes.R
import br.com.mobiletkbrazil.blueshoes.util.isValidEmail
import br.com.mobiletkbrazil.blueshoes.util.isValidPassword
import br.com.mobiletkbrazil.blueshoes.util.validate

class LoginActivity :
  FormEmailAndPasswordActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /**
     * Colocando configuração de validação de campo de email
     * para enquanto o usuário informa o conteúdo deste campo.
     */
    et_email.text.toString()
    et_email.validate(
      {
        it.isValidEmail()
      },
      getString(R.string.invalid_email)
    )

    /**
     * Colocando configuração de validação de campo de senha
     * para enquanto o usuário informa o conteúdo deste campo.
     */
    et_password.validate(
      {
        it.isValidPassword()
      },
      getString(R.string.invalid_password)
    )

    et_password.setOnEditorActionListener(this)
  }

  override fun getLayoutResourceID() = R.layout.content_login

  override fun backEndFakeDelay() {
    backEndFakeDelay(
      false,
      getString(R.string.invalid_login)
    )
  }

  override fun blockFields(status: Boolean) {
    et_email.isEnabled = !status
    et_password.isEnabled = !status
    bt_login.isEnabled = !status
  }

  override fun isMainButtonSending(status: Boolean) { /* Antigo isSignInGoing */
    bt_login.text =
      if (status)
        getString(R.string.sign_in_going)
      else
        getString(R.string.sign_in)
  }

  override fun isAbleToCallChangePrivacyPolicyConstraints() = ScreenUtils.isPortrait()

  override fun isConstraintToSiblingView(isKeyBoardOpened: Boolean) = isKeyBoardOpened

  override fun setConstraintsRelativeToSiblingView(
    constraintSet: ConstraintSet,
    privacyId: Int
  ) {

    /**
     * Se o teclado virtual estiver aberto, então
     * mude a configuração da View alvo
     * (tv_privacy_policy) para ficar vinculada a
     * View acima dela (tv_sign_up).
     */
    constraintSet.connect(
      privacyId,
      ConstraintLayout.LayoutParams.TOP,
      tv_sign_up.id,
      ConstraintLayout.LayoutParams.BOTTOM,
      (12 * ScreenUtils.getScreenDensity()).toInt()
    )
  }

  /* Listeners de clique */
  fun callForgotPasswordActivity(view: View) {
    val intent = Intent(
      this,
      ForgotPasswordActivity::class.java
    )

    startActivity(intent)
  }

  fun callSignUpActivity(view: View) {
    val intent = Intent(
      this,
      SignUpActivity::class.java
    )

    startActivity(intent)
  }
}
