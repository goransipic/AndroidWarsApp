package hr.goodapp.warsapp.ui.common

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import hr.goodapp.warsapp.ui.common.adaptersdelegates.DiffAdapter
import hr.goodapp.warsapp.ui.common.adaptersdelegates.DisplayableItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.EmptyDataItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem


val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T : DisplayableItem> ListDelegationAdapter<List<DisplayableItem>>.submitList(list: List<T>, showEmptyState : Boolean = true, centerEmptyView : Boolean = true) {
    items = updateList(list, showEmptyState, centerEmptyView)
    notifyDataSetChanged()
}

fun <T : DisplayableItem> DiffAdapter.submitList(list: List<T>, showEmptyState : Boolean = true, centerEmptyView : Boolean = true) {
    items = updateList(list,showEmptyState,centerEmptyView)
}

private fun <T : DisplayableItem> updateList(
    list: List<T>,
    showEmptyState: Boolean,
    centerEmptyView: Boolean
): List<DisplayableItem> {
    return if (list.isEmpty() && showEmptyState) {
        if (centerEmptyView){
            listOf(EmptyDataItem(centerEmptyView = centerEmptyView))
        }else{
            listOf<SetItemHeightItem>(SetItemHeightItem(10.px)) + listOf(EmptyDataItem(centerEmptyView = centerEmptyView))
        }
    } else {
        listOf<SetItemHeightItem>(SetItemHeightItem(10.px)) + list
    }
}

fun RecyclerView.init(): DslEventAdapterResult {
    layoutManager = LinearLayoutManager(context)
    val dslAdapter = getDSLAdapter()
    adapter = dslAdapter.adapter
    return dslAdapter
}

fun RecyclerView.initDiffAdapter(): DiffAdapterResult {
    layoutManager = LinearLayoutManager(context)
    val dslAdapter = getDiffDSLAdapter()
    adapter = dslAdapter.adapter
    return dslAdapter
}