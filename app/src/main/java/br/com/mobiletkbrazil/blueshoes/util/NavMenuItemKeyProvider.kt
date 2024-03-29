package br.com.mobiletkbrazil.blueshoes.util

import androidx.recyclerview.selection.ItemKeyProvider
import br.com.mobiletkbrazil.blueshoes.domain.NavMenuItem

class NavMenuItemKeyProvider(
  private val items: List<NavMenuItem>
) : ItemKeyProvider<Long>(SCOPE_MAPPED) {

  /**
   * Retorna a chave de seleção na posição fornecida do adaptador ou
   * então retorna null.
   */
  override fun getKey(position: Int) = items[position].id

  /**
   * Retorna a posição correspondente à chave de seleção, ou
   * RecyclerView.NO_POSITION em caso de null em getKey().
   */
  override fun getPosition(key: Long) = items.indexOf(
    items.single { item ->
      item.id == key
    }
  )
}
