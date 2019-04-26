package org.redciudadana.candidatos.screens.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_menu.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.screens.main.MainView

class MenuFragment: Fragment() {

    private val mainView: MainView
        get() = activity as MainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.title_menu)
        menu_item_diputados.setOnClickListener {
            mainView.showProfiles()
        }

        menu_item_mi_representante.setOnClickListener {
            mainView.showElectionTypes()
        }

        menu_item_congreso_datos.setOnClickListener {
            mainView.showCongressData()
        }

        menu_item_noticias.setOnClickListener {
            mainView.showNews()
        }
    }
}