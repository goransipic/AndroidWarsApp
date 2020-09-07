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
import hr.goodapp.warsapp.databinding.CardLayoutBinding
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
            binding.textView.text = item.first
        }
    }

data class ThreeRowItem(val first: String,
                        val second: String,
                        val third: String,
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

fun cardSignedDelegate(liveData: MutableLiveData<Event<Any>>) = adapterDelegate<CardDelegateItem, DisplayableItem>(R.layout.card_layout){
    val binding =  CardLayoutBinding.bind(itemView)
    binding.root.setOnClickListener {
        liveData.value = Event(item)
    }

    bind {
        binding.cardLayoutEmbed.textView.text = item.first
        binding.cardLayoutEmbed.textView4.text = item.second
        binding.cardLayoutEmbed.imageView3.setImageResource(item.image)
    }
}

data class CardDelegateItem(val first: String, val second: String, @DrawableRes val image: Int = R.drawable.rounded_background, val payload: Any? = null) : DisplayableItem {
    override fun getItemId(): Long {
        return first.hashCode().toLong()
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}

