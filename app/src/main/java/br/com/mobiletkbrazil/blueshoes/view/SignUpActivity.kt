package br.com.mobiletkbrazil.blueshoes.view

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.blankj.utilcode.util.ScreenUtils
import br.com.mobiletkbrazil.blueshoes.R
import br.com.mobiletkbrazil.blueshoes.util.isValidEmail
import br.com.mobiletkbrazil.blueshoes.util.isValidPassword
import br.com.mobiletkbrazil.blueshoes.util.validate
import kotlinx.android.synthetic.main.content_sign_up.*

class SignUpActivity :
  FormEmailAndPasswordActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    /**
     * Colocando configuração de validação de campo de email
     * para enquanto o usuário informa o conteúdo deste campo.
     */
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

    /**
     * Colocando configuração de validação de campo de
     * confirmação de senha para enquanto o usuário informa o
     * conteúdo deste campo.
     */
    et_confirm_password.validate(
      {
        /**
         * O toString() em et_password.text.toString() é
         * necessário, caso contrário a validação falha
         * mesmo quando é para ser ok.
         */
        (et_password.text.isNotEmpty()
          && it == et_password.text.toString())
          || et_password.text.isEmpty()
      },
      getString(R.string.invalid_confirmed_password)
    )

    et_confirm_password.setOnEditorActionListener(this)
  }

  override fun getLayoutResourceID() = R.layout.content_sign_up

  override fun backEndFakeDelay() {
    backEndFakeDelay(
      false,
      getString(R.string.invalid_sign_up_email)
    )
  }

  override fun blockFields(status: Boolean) {
    et_email.isEnabled = !status
    et_password.isEnabled = !status
    et_confirm_password.isEnabled = !status
    bt_sign_up.isEnabled = !status
  }

  override fun isMainButtonSending(status: Boolean) { /* Antigo isSignInGoing */
    bt_sign_up.text =
      if (status)
        getString(R.string.sign_up_going)
      else
        getString(R.string.sign_up)
  }

  override fun isConstraintToSiblingView(isKeyBoardOpened: Boolean) =
    isKeyBoardOpened || ScreenUtils.isLandscape()

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
      bt_sign_up.id,
      ConstraintLayout.LayoutParams.BOTTOM,
      (12 * ScreenUtils.getScreenDensity()).toInt()
    )
  }

  fun callLoginActivity(view: View) {
    finish()
  }
}
