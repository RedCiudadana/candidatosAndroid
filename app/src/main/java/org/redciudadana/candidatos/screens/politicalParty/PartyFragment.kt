package org.redciudadana.candidatos.screens.politicalParty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_recycler.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.Party
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import org.redciudadana.candidatos.utils.views.initializeRecycler
import java.lang.ref.WeakReference

class PartyFragment: BaseFragment<PartyContract.View, PartyContract.Presenter, MainView>(), PartyContract.View {

    override var mPresenter: PartyContract.Presenter = PartyPresenter()
    var mPartyAdapter: WeakReference<PartyAdapter>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecycler(context, recycler_view)

    }

    override fun setTitle() {
        mActivityView?.setTitle("Partidos")
    }

    override fun presentPartyList(partyList: List<Party>) {
        var partyAdapter = mPartyAdapter?.get()
        if (partyAdapter == null) {
            partyAdapter = PartyAdapter(context!!, mPresenter, partyList)
        }
        recycler_view.adapter = partyAdapter
        mPartyAdapter = WeakReference(partyAdapter)
    }
}