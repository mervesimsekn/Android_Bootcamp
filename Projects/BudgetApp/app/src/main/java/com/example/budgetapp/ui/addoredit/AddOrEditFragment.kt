package com.example.budgetapp.ui.addoredit

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.budgetapp.R
import com.example.budgetapp.common.viewBinding
import com.example.budgetapp.data.model.IncomeExpense
import com.example.budgetapp.databinding.FragmentAddOrEditBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddOrEditFragment : BottomSheetDialogFragment(R.layout.fragment_add_or_edit) {

    private val binding by viewBinding(FragmentAddOrEditBinding::bind)

    private val args by navArgs<AddOrEditFragmentArgs>()

    private lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = Firebase.firestore

        with(binding) {
            args.incomeExpense?.let {
                etTitle.setText(it.title)
                etAmount.setText(it.amount.toString())

                if (it.incomeExpenseType == true) {
                    isIncomeButton.isChecked = true
                } else {
                    isExpenseButton.isChecked = true
                }

                btnSave.visibility = View.VISIBLE
                btnAdd.visibility = View.GONE
            }

            btnAdd.setOnClickListener {
                val title = etTitle.text.toString()
                val amount = etAmount.text.toString()
                val isIncomeExpense = isIncomeButton.isChecked

                if (title.isNotEmpty() && amount.isNotEmpty()) {
                    addIncomeExpense(title, amount.toDouble(), isIncomeExpense)
                } else {
                    //
                }
            }

            btnSave.setOnClickListener {
                val title = etTitle.text.toString()
                val amount = etAmount.text.toString()
                val isIncomeExpense = isIncomeButton.isChecked


                if (title.isNotEmpty() && amount.isNotEmpty()) {
                    saveIncomeExpense(
                        (args.incomeExpense?.docId ?: "") as String,
                        title,
                        amount.toDouble(),
                        isIncomeExpense
                    )
                } else {
                    //
                }


            }
        }
    }

    private fun addIncomeExpense(
        title: String,
        amount: Double,
        isIncomeExpense: Boolean
    ) {
        val budget = IncomeExpense(
            docId = null,
            title = title,
            amount = amount,
            category = null,
            incomeExpenseType = isIncomeExpense
        )
        db.collection("income_expense").document(title).set(budget).addOnSuccessListener {
            findNavController().navigateUp()
        }.addOnFailureListener {
            //
        }
    }

    private fun saveIncomeExpense(
        docId: String,
        title: String,
        amount: Double,
        isIncomeExpense: Boolean
    ) {
        db.collection("income_expense").document(docId)
            .update(
                mapOf(
                    "title" to title,
                    "amount" to amount,
                    "incomeExpenseType" to isIncomeExpense
                )
            )
            .addOnSuccessListener {
                findNavController().navigateUp()
            }
            .addOnFailureListener {
                //
            }
    }


}