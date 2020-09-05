package hr.goodapp.warsapp.ui.common

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment(@LayoutRes val layoutResource : Int) : Fragment(layoutResource) {
}