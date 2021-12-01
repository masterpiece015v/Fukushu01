package com.example.fukushu01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fukushu01.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.where

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    class GakuseiAdapter(var data : List<Gakusei>):
    RecyclerView.Adapter<GakuseiAdapter.ViewHolder>(){
        class ViewHolder(view:View)
            :RecyclerView.ViewHolder(view){

                val textKurasu = view.findViewById<TextView>(R.id.textKurasu)
                val textNamae = view.findViewById<TextView>(R.id.textNamae)
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ViewHolder {
            //row_item.xmlをviewholderにする
            val view = LayoutInflater.from( parent.context ).inflate(R.layout.row_item,parent,false)
            return ViewHolder( view )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //data(List)をviewholderに入れる
            holder.textKurasu.text = data[position].kurasu
            holder.textNamae.text = data[position].namae
        }

        override fun getItemCount(): Int {
            //dataの個数を返却
            return data.size
        }
    }


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val realm = Realm.getDefaultInstance()
        val realmResult = realm.where<Gakusei>().findAll()

        val list = arrayListOf<Gakusei>()

        realmResult.forEach{
            val g = Gakusei(it.id,it.kurasu,it.namae)
            list.add( g )
        }

        val adapter = GakuseiAdapter(list)

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(context)

        binding.buttonGakuseiAdd.setOnClickListener{

            val dialog = InputDialog(
                { kurasumei,gakuseimei->
                    val g = Gakusei( 0,gakuseimei,gakuseimei)
                    list.add( g )

                },{
                    Snackbar.make(binding.root ,"Cancel", Snackbar.LENGTH_SHORT)
                }
            )

            dialog.show( parentFragmentManager,"dialog")
        }
        //binding.textView.setOnClickListener{
        //    val action = MainFragmentDirections.actionMainFragmentToNextFragment()
        //    findNavController().navigate(action)
        //}
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}