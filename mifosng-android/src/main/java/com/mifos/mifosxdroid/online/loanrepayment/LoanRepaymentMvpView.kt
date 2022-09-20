package com.mifos.mifosxdroid.online.loanrepayment

import com.mifos.mifosxdroid.base.MvpView
import com.mifos.objects.accounts.loan.LoanRepaymentResponse
import com.mifos.objects.accounts.loan.LoanWithAssociations
import com.mifos.objects.templates.loans.LoanRepaymentTemplate

/**
 * Created by Rajan Maurya on 8/6/16.
 */
interface LoanRepaymentMvpView : MvpView {
    fun showLoanRepayTemplate(loanRepaymentTemplate: LoanRepaymentTemplate?)
    fun showPaymentSubmittedSuccessfully(loanRepaymentResponse: LoanRepaymentResponse?)
    fun checkLoanRepaymentStatusInDatabase()
    fun showLoanRepaymentExistInDatabase()
    fun showLoanRepaymentDoesNotExistInDatabase()
    fun showError(errorMessage: Int)

    // For Repayment Schedule
    fun showLoanRepaySchedule(loanWithAssociations: LoanWithAssociations)
    fun showFetchingError(s: String?)

    // SendPaymentLink
    fun showPaymentSentSuccessfully(res: String?)
}