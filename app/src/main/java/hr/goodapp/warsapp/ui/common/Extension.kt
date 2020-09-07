package hr.goodapp.warsapp.ui.common

import android.app.ProgressDialog
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import hr.goodapp.warsapp.R
import hr.goodapp.warsapp.ui.common.adaptersdelegates.DiffAdapter
import hr.goodapp.warsapp.ui.common.adaptersdelegates.DisplayableItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.EmptyDataItem
import hr.goodapp.warsapp.ui.common.adaptersdelegates.SetItemHeightItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.humanFormat : String get() = when (this) {
    1 -> "I"
    2 -> "II"
    3 -> "III"
    4 -> "IV"
    5 -> "V"
    6 -> "VI"
    7 -> "VII"
    8 -> "VIII"
    9 -> "IX"
    10 -> "X"
    11 -> "XI"
    12 -> "XII"
    13 -> "XIII"
    else -> this.toString()
}

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

fun <T> ViewModel.launchLiveData(block: suspend () -> T) = liveData {
    try {
        emit(Resource.forLoading())
        val t = withContext(Dispatchers.Default) { block() }
        emit(Resource.forSuccess(t))
    } catch (e: Throwable) {
        Timber.e(e)
        emit(Resource.forFailure(e))
    }
}

fun Fragment.getProgressBar(): ProgressDialog {
    val progress = ProgressDialog(context, R.style.ThemeOverlay_MaterialComponents_Dialog_Alert)
    progress.setMessage(getString(R.string.please_wait))
    progress.isIndeterminate = true
    progress.setCancelable(false)
    progress.setOnCancelListener(null)

    return progress
}

fun <T> Fragment.listenLiveData(
    liveData: LiveData<Resource<T>>,
    onLoading: () -> Unit = { view?.findViewById<View>(R.id.loadingView)?.visibility = View.VISIBLE
                              view?.findViewById<View>(R.id.errorView)?.visibility = View.GONE},
    onFailure: (Throwable) -> Boolean = {
        view?.findViewById<View>(R.id.errorView)?.visibility = View.VISIBLE
        false },
    onSuccess: (T) -> Unit
) {
    liveData.observe(
        viewLifecycleOwner,
        Observer {
            when(it.state){
                State.SUCCESS ->  {view?.findViewById<View>(R.id.loadingView)?.visibility = View.GONE
                                   onSuccess(it.value!!)}
                State.FAILURE ->  {view?.findViewById<View>(R.id.loadingView)?.visibility = View.GONE
                                    onFailure(it.exception!!)}
                State.LOADING ->  onLoading()}
        })
}

fun <T> Fragment.listenEventData(liveData: LiveData<Event<T>>, tempResult: (T) -> Unit) {

    liveData.observe(viewLifecycleOwner, EventObserver {
        tempResult(it)
    })
}