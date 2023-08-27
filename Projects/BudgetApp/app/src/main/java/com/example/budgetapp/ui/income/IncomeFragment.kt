package com.example.budgetapp.ui.income

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
import com.example.budgetapp.databinding.FragmentIncomeBinding
import com.example.budgetapp.ui.summary.SummaryAdapter
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class IncomeFragment : Fragment(R.layout.fragment_income) {

    private val binding by viewBinding(FragmentIncomeBinding::bind)

    private val incomeAdapter by lazy { SummaryAdapter(::onIncomeClick) }

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
                        findNavController().navigate(R.id.incomeToSignIn)
                        true
                    }

                    else -> false
                }
            }

            rvIncome.adapter = incomeAdapter
        }

        listenIncome()
    }

    private fun listenIncome() {
        db.collection("income_expense").whereEqualTo("incomeExpenseType", true)
            .addSnapshotListener { snapshot, e ->
                val templist = arrayListOf<IncomeExpense>()
                var totalIncome: Double = 0.0

                snapshot?.forEach { document ->
                    templist.add(
                        IncomeExpense(
                            document.id,
                            document.get("title") as String,
                            (document.get("amount") as Number).toDouble(),
                            document.get("incomeExpenseType") as Boolean?,
                            document.get("category") as String?
                        )
                    )
                    totalIncome -= document.get("amount") as Double
                    with(binding) {
                        tvTotal.text = "${totalIncome} ₺"
                        tvTotal.setTextColor(Color.GREEN)
                    }
                }
                incomeAdapter.submitList(templist)
            }
    }

    private fun onIncomeClick(incomeExpense: IncomeExpense) {
        val action = IncomeFragmentDirections.incomeToAddOrEdit().setIncomeExpense(incomeExpense)
        findNavController().navigate(action)
    }
}