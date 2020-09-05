package hr.goodapp.warsapp.ui.common

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes val layoutResource : Int) : Fragment(layoutResource) {
}