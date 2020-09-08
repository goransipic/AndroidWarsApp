package hr.goodapp.warsapp.ui.common.adaptersdelegates

import android.graphics.Color
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.MutableLiveData
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.databinding.*
import hr.goodapp.warsapp.ui.common.Event
import hr.goodapp.warsapp.ui.common.px


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

data class EmptyDataItem(
    @StringRes val text: Int = R.string.empty_text,
    val centerEmptyView: Boolean = false
) : DisplayableItem


fun itemHeightDelegate() =
    adapterDelegate<SetItemHeightItem, DisplayableItem>(R.layout.set_item_height) {
        val binding = SetItemHeightBinding.bind(itemView)
        bind {
            binding.frSIH.updatePadding(top = item.height)
            //frSIH.updatePadding(top = item.height)
        }
    }

data class SetItemHeightItem(val height: Int = 10.px, val id: Long = 0L) : DisplayableItem {
    override fun getItemId(): Long {
        return id
    }
}

fun twoRowDelegate() =
    adapterDelegate<TwoRowItem, DisplayableItem>(R.layout.two_row) {
        val binding = TwoRowBinding.bind(itemView)
        bind {
            binding.tvTitle.text = item.title
            binding.tvSubtitle.text = item.subtitle
            //frSIH.updatePadding(top = item.height)
        }
    }

data class TwoRowItem(val title : String, val subtitle: String) : DisplayableItem

fun singleRowDelegate() =
    adapterDelegate<SingleRowItem, DisplayableItem>(R.layout.single_row) {
        val binding = SingleRowBinding.bind(itemView)
        bind {
            binding.tvTitle.text = item.title
            //frSIH.updatePadding(top = item.height)
        }
    }

data class SingleRowItem(val title : String) : DisplayableItem

fun cardSignedDelegate(liveData: MutableLiveData<Event<Any>>) = adapterDelegate<CardDelegateItem, DisplayableItem>(
    R.layout.card_layout
){
    val binding =  CardLayoutBinding.bind(itemView)
    binding.root.setOnClickListener {
        liveData.value = Event(item.payload!!)
    }

    bind {
        binding.cardLayoutEmbed.textViewFirst.text = item.first
        binding.cardLayoutEmbed.textViewSecond.text = item.second
        val background = binding.cardLayoutEmbed.textRound.background
        val wrappedDrawable = DrawableCompat.wrap(background)
        if(item.third in 1..49) {
            DrawableCompat.setTint(wrappedDrawable, Color.GREEN)
        }
        if(item.third in 50..79) {
            DrawableCompat.setTint(wrappedDrawable, Color.LTGRAY)
        }
        if(item.third > 80) {
            DrawableCompat.setTint(wrappedDrawable, Color.RED)
        }
        binding.cardLayoutEmbed.textRound.text = "${item.third} kg"
    }
}

data class CardDelegateItem(
    val first: String,
    val second: String,
    val third: Int,
    @DrawableRes val image: Int = R.drawable.rounded_background,
    val payload: Any? = null
) : DisplayableItem {
    override fun getItemId(): Long {
        return first.hashCode().toLong()
    }

    override fun getItemHash(): Int {
        return hashCode()
    }
}

