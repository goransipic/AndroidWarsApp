package hr.goodapp.warsapp.ui.common.adaptersdelegates

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.EmptyDataBinding
import hr.goodapp.warsapp.databinding.SetItemHeightBinding
import hr.goodapp.warsapp.databinding.ThirdColumnItemBinding
import hr.goodapp.warsapp.ui.common.Event
import hr.goodapp.warsapp.ui.common.px

fun twoRowAdapterDelegate(liveData: MutableLiveData<Event<Any>>) =
    adapterDelegate<ThreeRowItem, DisplayableItem>(R.layout.third_column_item) {
        val binding = ThirdColumnItemBinding.bind(itemView)

        itemView.setOnClickListener {
            liveData.postValue(Event(item))
        }

        bind {

        }
    }

data class ThreeRowItem(val first: String,
                        val second: String,
                        val payload: Any? = null,
                        @DrawableRes val placeholder: Int = R.drawable.ic_launcher_background) :
    DisplayableItem {
    override fun getItemId(): Long {
        return first.hashCode().toLong()
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}


fun emptyDelegate() =
    adapterDelegate<EmptyDataItem, DisplayableItem>(R.layout.empty_data) {
        val binding =  EmptyDataBinding.bind(itemView)

        bind {
            binding.textView22.setText(item.text)
            if (!item.centerEmptyView){
                with(ConstraintSet()){
                    clone(binding.constraintLayout)
                    clear(R.id.textView22, ConstraintSet.BOTTOM)
                    applyTo(binding.constraintLayout)
                }
                val layoutParams = binding.constraintLayout.layoutParams
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
        }
    }

data class EmptyDataItem(@StringRes val text : Int = R.string.empty_text, val centerEmptyView : Boolean = false ) : DisplayableItem


fun itemHeightDelegate() =
    adapterDelegate<SetItemHeightItem, DisplayableItem>(R.layout.set_item_height) {
        val binding = SetItemHeightBinding.bind(itemView)
        bind {
            binding.frSIH.updatePadding(top = item.height)
            //frSIH.updatePadding(top = item.height)
        }
    }

data class SetItemHeightItem(val height: Int = 10.px, val id : Long = 0L) : DisplayableItem {
    override fun getItemId(): Long {
        return id
    }
}

