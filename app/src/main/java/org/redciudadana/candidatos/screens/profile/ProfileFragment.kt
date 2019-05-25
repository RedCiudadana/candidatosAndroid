package org.redciudadana.candidatos.screens.profile

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexvasilkov.foldablelayout.UnfoldableView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_diputado.*
import kotlinx.android.synthetic.main.fragment_diputado_assistance.*
import kotlinx.android.synthetic.main.fragment_diputado_general_info.*
import kotlinx.android.synthetic.main.fragment_diputado_history.*
import kotlinx.android.synthetic.main.fragment_diputado_voting.*
import org.redciudadana.candidatos.R
import org.redciudadana.candidatos.data.models.*
import org.redciudadana.candidatos.screens.main.MainView
import org.redciudadana.candidatos.utils.glide.GlideApp
import org.redciudadana.candidatos.utils.glide.RoundCornerTransformation
import org.redciudadana.candidatos.utils.mvp.BaseFragment
import java.lang.ref.WeakReference

class ProfileFragment: BaseFragment<ProfileContract.View, ProfileContract.Presenter, MainView>(), ProfileContract.View {

    override var mPresenter: ProfileContract.Presenter = DiputadoPresenter()

    var mHistoryAdapter: WeakReference<ProfileHistoryAdapter>? = null
    var mVotingAdapter: WeakReference<ProfileVotingAdapter>? = null

    override fun setTitle() {
        mActivityView?.setTitle("Candidato")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_diputado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        diputado_information_list.adapter = DiputadoOptionsAdapter(this)
        initUnfoldable()
    }

    private fun initUnfoldable() {
        touch_interceptor.isClickable = false
        details_layout.visibility = INVISIBLE
        unfoldable_view.setOnFoldingListener(object : UnfoldableView.SimpleFoldingListener() {
            override fun onUnfolding(unfoldableView: UnfoldableView?) {
                view?.findViewById<View>(R.id.touch_interceptor)?.let {
                    it.isClickable = true
                    details_layout.visibility = VISIBLE
                }

            }

            override fun onUnfolded(unfoldableView: UnfoldableView?) {
                view?.findViewById<View>(R.id.touch_interceptor)?.run {
                    isClickable = false
                }
            }

            override fun onFoldingBack(unfoldableView: UnfoldableView?) {
                view?.findViewById<View>(R.id.touch_interceptor)?.run {
                    isClickable = true
                }
            }

            override fun onFoldedBack(unfoldableView: UnfoldableView?) {
                view?.findViewById<View>(R.id.touch_interceptor)?.let {
                    it.isClickable = false
                    details_layout.visibility = INVISIBLE
                }
            }
        })
        mActivityView?.setOnBackListener(this::onBackPressed)
        diputado_show_overview.setOnClickListener {
            unfoldable_view.foldBack()
        }
    }

    private fun onBackPressed(): Boolean {
        view?.findViewById<UnfoldableView>(R.id.unfoldable_view)?.run {
            if (isUnfolded || isUnfolding) {
                foldBack()
                return true
            }
        }
        return false
    }

    override fun showProfile(profile: Profile) {
        context?.let {
            GlideApp
                .with(it)
                .load(profile.fotoUrl)
                .transform(RoundCornerTransformation(it.resources))
                .fallback(R.drawable.profile)
                .error(R.drawable.profile)
                .into(diputado_face_image)
        }
        diputado_name.text = profile.nombre
        showSquareDetail(profile)
        button_facebook.setOnClickListener { mPresenter.onFacebookPress() }
        button_twitter.setOnClickListener { mPresenter.onTwitterPress() }
        button_call.setOnClickListener { mPresenter.onPhonePress() }
    }

    private fun showSquareDetail(profile: Profile) {
        val text = when (profile.electionType) {
            ElectionType.PRESIDENT -> "Presidente"
            ElectionType.VICEPRESIDENT -> "Vicepresidente"
            ElectionType.NATIONAL_LISTING -> if (profile.casilla != null) "Casilla ${profile.casilla}" else "Listado nacional"
            ElectionType.PARLACEN -> "Parlacen"
            ElectionType.DISTRICT -> profile.distrito
            ElectionType.MAYOR -> profile.municipio
            null -> ""
        }
        diputado_department.text = text
    }

    override fun showParty(party: Party) {
        context?.let {
            GlideApp
                .with(it)
                .load(party.logoUrl)
                .into(diputado_partido_image)
        }
    }

    override fun onOptionPress(view: View, position: Int?) {
        mPresenter.onOptionPress(view, position)
    }


    override fun showGeneralInformation(view: View, profile: Profile) {
        inflateIntoDetails(R.layout.fragment_diputado_general_info, "Información general", R.drawable.icon_document_white)
        val parsedText = fromHtml(profile.profileInfo?.biography)
        diputado_general_info_text.text = parsedText
        unfoldDetails(view)
    }

    override fun showHistory(view: View, historyEntryList: List<HistoryEntry>?) {
        inflateIntoDetails(R.layout.fragment_diputado_history, "Historial político", R.drawable.icon_history_white)
        context?.let {
            val mLayoutManager = LinearLayoutManager(context)
            val historyAdapter= ProfileHistoryAdapter(this, historyEntryList)
            mHistoryAdapter = WeakReference(historyAdapter)
            history_recycler.setHasFixedSize(true)
            history_recycler.layoutManager = mLayoutManager
            history_recycler.addItemDecoration(
                DividerItemDecoration(
                    context,
                    mLayoutManager.orientation
                )
            )
            history_recycler.adapter = historyAdapter

        }
        unfoldDetails(view)
    }

    override fun updateHistory(historyEntryList: List<HistoryEntry>?) {
        mHistoryAdapter?.get()?.let {
            it.historyList = historyEntryList
        }
        if (historyEntryList == null) {
            showError("No se pudo obtener la información")
        }
    }

    fun createData(percentageString: String?): PieData {
        val percentage = percentageString?.replace("%", "")?.toFloatOrNull() ?: 0f
        val missing = 100f - percentage
        val entries = listOf(
            PieEntry(percentage, "Asistencia"),
            PieEntry(missing, "Ausencia")
        )
        val dataSet = PieDataSet(entries, "")
        dataSet.setColors(intArrayOf(R.color.chart_assistance, R.color.chart_missing), context)
        dataSet.setValueFormatter { value, _, _, _-> "$value%" }
        return PieData(dataSet)
    }

    override fun showAssistance(view: View, assistance: Assistance?) {
        inflateIntoDetails(R.layout.fragment_diputado_assistance, "Asistencia", R.drawable.icon_people_white)
        val data = createData(assistance?.porcentaje)
        val description = Description()
        description.text = ""
        diputado_assistance_chart.description= description
        diputado_assistance_chart.setEntryLabelColor(ContextCompat.getColor(context!!, R.color.chart_label))
        diputado_assistance_chart.data = data
        diputado_assistance_chart.invalidate()
        unfoldDetails(view)
    }

    override fun updateAssistance(assistance: Assistance?) {
        view?.findViewById<PieChart>(R.id.diputado_assistance_chart)?.let {
            val data = createData(assistance?.porcentaje)
            it.data = data
            it.invalidate()
        }

    }

    override fun showVoting(view: View, voting: List<Voting>?) {
        inflateIntoDetails(R.layout.fragment_diputado_voting, "Votación", R.drawable.icon_check_white)
        context?.let {
            val mLayoutManager = LinearLayoutManager(context)
            val votingAdapter= ProfileVotingAdapter(this, voting)
            mVotingAdapter= WeakReference(votingAdapter)
            voting_recycler.setHasFixedSize(true)
            voting_recycler.layoutManager = mLayoutManager
            voting_recycler.addItemDecoration(
                DividerItemDecoration(
                    context,
                    mLayoutManager.orientation
                )
            )
            voting_recycler.adapter = votingAdapter
        }
        unfoldDetails(view)
    }

    override fun updateVoting(voting: List<Voting>?) {
        mVotingAdapter?.get()?.votingList = voting
    }

    private fun inflateIntoDetails(layout: Int, title: String, icon: Int) {
        details_content.removeAllViews()
        LayoutInflater.from(context).inflate(layout, details_content, true)
        details_title.text = title
        context?.let {
            GlideApp
                .with(it)
                .load(icon)
                .into(details_title_icon)
        }
        clearFindViewByIdCache()
    }

    private fun unfoldDetails(view: View) {
        unfoldable_view.unfold(view, details_layout)
    }


    private fun fromHtml(text: String?): CharSequence? {
        if (text == null) return "Información no disponible"
        return if (Build.VERSION.SDK_INT < 24) {
            Html.fromHtml(text)
        } else {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        }
    }

}