package hr.goodapp.warsapp.ui.common.adaptersdelegates

interface DisplayableItem {
    fun getItemId(): Long = 0

    fun getItemHash(): Int = 0
}