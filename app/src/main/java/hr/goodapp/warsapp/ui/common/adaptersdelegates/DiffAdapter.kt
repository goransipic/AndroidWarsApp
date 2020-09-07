package hr.goodapp.warsapp.ui.common.adaptersdelegates

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import java.util.*


class DiffAdapter(vararg delegates : AdapterDelegate<List<DisplayableItem>>) : AsyncListDifferDelegationAdapter<DisplayableItem>(DIFF_CALLBACK, *delegates) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DisplayableItem> =
            object : DiffUtil.ItemCallback<DisplayableItem>() {
                override fun areItemsTheSame(
                    oldItem: DisplayableItem,
                    newItem: DisplayableItem
                ): Boolean {
                    return oldItem.getItemId() == newItem.getItemId()
                }

                override fun areContentsTheSame(
                    oldItem: DisplayableItem,
                    newItem: DisplayableItem
                ): Boolean {
                    return oldItem.getItemHash() == newItem.getItemHash()
                }
            }
    }
}