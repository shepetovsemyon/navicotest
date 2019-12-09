package ru.shepetov.navicotest

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.shepetov.navicotest.R

abstract class BaseFragment: Fragment() {
    protected val navigator: NavController?
        get() = Navigation.findNavController(
            activity!!,
            R.id.applicationContainer
        )
}