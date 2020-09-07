package hr.goodapp.warsapp.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import hr.goodapp.warsapp.ui.common.adaptersdelegates.*

fun getDelegates(liveData: MutableLiveData<Event<Any>>)
        = arrayOf( twoRowAdapterDelegate(liveData), emptyDelegate(), itemHeightDelegate(), cardSignedDelegate(liveData))

fun getDSLAdapter(): DslEventAdapterResult {

    val liveData = MutableLiveData<Event<Any>>()

    val delegationAdapter = ListDelegationAdapter<List<DisplayableItem>>(
        *getDelegates(liveData)
    )

    return DslEventAdapterResult(delegationAdapter, liveData)
}

fun getDiffDSLAdapter() : DiffAdapterResult {
    val liveData = MutableLiveData<Event<Any>>()
    val diffAdapter = DiffAdapter(*getDelegates(liveData))
    return DiffAdapterResult(diffAdapter,liveData)
}

data class DiffAdapterResult(val adapter: DiffAdapter,
                       val liveData: LiveData<Event<Any>>)

data class DslEventAdapterResult(
    val adapter: ListDelegationAdapter<List<DisplayableItem>>,
    val liveData: LiveData<Event<Any>>
)

