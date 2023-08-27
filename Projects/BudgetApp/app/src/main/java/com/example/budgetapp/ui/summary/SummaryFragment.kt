package com.example.budgetapp.ui.summary

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.budgetapp.R
import com.example.budgetapp.common.viewBinding
import com.example.budgetapp.data.model.IncomeExpense
import com.example.budgetapp.databinding.FragmentSummaryBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SummaryFragment : Fragment(R.layout.fragment_summary) {

    private val binding by viewBinding(FragmentSummaryBinding::bind)

    private val summaryAdapter by lazy { SummaryAdapter(::onIncomeExpenseClick) }

    private lateinit var db: FirebaseFirestore

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "inflater.inflate(R.menu.toolbar_menu, menu)",
            "com.example.budgetapp.R"
        )
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore

        with(binding) {
            toolbar.inflateMenu(R.menu.toolbar_menu)

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_exit -> {
                        Firebase.auth.signOut()
                        findNavController().navigate(R.id.summaryToSignIn)
                        true
                    }

                    else -> false
                }
            }

            rvSummary.adapter = summaryAdapter

            fabAdd.setOnClickListener {
                findNavController().navigate(R.id.summaryToAddOrEdit)
            }
        }

        listenBudget()
    }

    private fun listenBudget() {
        db.collection("income_expense").addSnapshotListener { snapshot, e ->
            val tempList = arrayListOf<IncomeExpense>()
            var totalBudget = 0.0

            snapshot?.forEach { document ->
                tempList.add(
                    IncomeExpense(
                        document.id,
                        document.get("title") as String,
                        (document.get("amount") as Number).toDouble(),
                        document.get("incomeExpenseType") as Boolean?,
                        document.get("category") as String?
                    )
                )
                if (document.get("incomeExpenseType") as Boolean) {
                    totalBudget += document.get("amount") as Double
                } else {
                    totalBudget -= document.get("amount") as Double
                }
                with(binding) {
                    if (totalBudget > 0) {
                        tvTotal.text = "+${totalBudget} ₺"
                        tvTotal.setTextColor(Color.GREEN)
                    } else {
                        tvTotal.text = "${totalBudget} ₺"
                        tvTotal.setTextColor(Color.RED)
                    }
                }
            }
            summaryAdapter.submitList(tempList)
        }
    }

    private fun onIncomeExpenseClick(incomeExpense: IncomeExpense) {
        val action = SummaryFragmentDirections.summaryToAddOrEdit().setIncomeExpense(incomeExpense)
        findNavController().navigate(action)
    }

}