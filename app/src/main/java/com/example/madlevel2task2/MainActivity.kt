package com.example.madlevel2task2

import QuestionAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout.make
import android.view.LayoutInflater
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.question.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

   private fun initViews() {
       binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
       binding.recyclerView.adapter = questionAdapter
       binding.recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

       createItemTouchHelper().attachToRecyclerView(recyclerView)
       createQuestions()
       questionAdapter.notifyDataSetChanged()
   }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(questions[position].answer == direction){
                    questions.removeAt(position)
                } else if(questions[position].answer == ItemTouchHelper.LEFT || questions[position].answer == ItemTouchHelper.RIGHT){

                }
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun createQuestions() {
        questions.add(Question("A 'val' and 'var' are the same.", ItemTouchHelper.RIGHT))
        questions.add(Question("Mobile Application Development grants 12 ECTS.", ItemTouchHelper.LEFT))
        questions.add(Question("A Unit in Kotlin corresponds to a void in Java.", ItemTouchHelper.RIGHT))
        questions.add(Question("In Kotlin 'when' replaces the 'switch' operator in Java", ItemTouchHelper.RIGHT))
    }
}