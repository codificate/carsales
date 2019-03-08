package com.chileautos.carsales.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.chileautos.carsales.R
import com.chileautos.carsales.data.db.entity.CarPost
import com.chileautos.carsales.ui.base.ScopedFragment
import com.chileautos.carsales.ui.viewholder.CarPostItem
import com.chileautos.carsales.ui.model.CarPostViewModel
import com.chileautos.carsales.ui.model.CarPostViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.car_post_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CarPostListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: CarPostViewModelFactory by instance()
    private lateinit var viewModel: CarPostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.car_post_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CarPostViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main){
        val carPostResult = viewModel.fetchPosts.await()

        carPostResult.observe(this@CarPostListFragment, Observer { posts ->
            if (posts.isEmpty()) return@Observer

            group_loading.visibility = View.GONE

            numberResults.text = posts.size.toString().plus(activity?.getString(R.string.car_post_list_fragment))
            initRecyclerView(posts.toCarPostItem())

        })
    }

    private fun List<CarPost>.toCarPostItem() : List<CarPostItem> {
        return this.map {
            CarPostItem(it)
        }
    }

    private fun initRecyclerView(items: List<CarPostItem>){
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CarPostListFragment.context)
            adapter = groupAdapter
        }

    }
}