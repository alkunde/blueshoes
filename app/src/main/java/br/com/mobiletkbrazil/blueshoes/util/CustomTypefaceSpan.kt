package br.com.mobiletkbrazil.blueshoes.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class CustomTypefaceSpan(typeFace: Typeface) :
  TypefaceSpan("") {

  private val newTypeFace = typeFace

  override fun updateDrawState(paint: TextPaint) {
    applyCustomTypeFace(paint, newTypeFace)
  }

  override fun updateMeasureState(paint: TextPaint) {
    applyCustomTypeFace(paint, newTypeFace)
  }

  private fun applyCustomTypeFace(
    paint: Paint,
    typeface: Typeface
  ) {
    val styleAnterior: Int
    val typefaceAnterior = paint.typeface

    styleAnterior = typefaceAnterior?.style ?: 0

    /**
     * Para verificar a compatibilidade de estilos.
     */
    val fake = styleAnterior and typeface.style.inv()

    /**
     * Verifica se a fonte mais atual já está de acordo
     * com a anterior em termos de "texto em negrito",
     * caso não, atualiza.
     */
    if (fake and Typeface.BOLD != 0) {
      paint.isFakeBoldText = true
    }

    /**
     * Verifica se a fonte mais atual já está de acordo
     * com a anterior em termos de "texto em itálico",
     * caso não, atualiza.
     */
    if (fake and Typeface.ITALIC != 0) {
      paint.textSkewX = -0.25f
    }

    /**
     * Aplica a fonte.
     */
    paint.typeface = typeface
  }
}
