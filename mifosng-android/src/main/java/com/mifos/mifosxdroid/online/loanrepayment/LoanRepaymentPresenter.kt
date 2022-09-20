package com.mifos.mifosxdroid.online.loanrepayment

import com.mifos.api.DataManager
import com.mifos.api.datamanager.DataManagerLoan
import com.mifos.mifosxdroid.R
import com.mifos.mifosxdroid.base.BasePresenter
import com.mifos.objects.accounts.loan.LoanRepaymentRequest
import com.mifos.objects.accounts.loan.LoanRepaymentResponse
import com.mifos.objects.accounts.loan.LoanWithAssociations
import com.mifos.objects.accounts.loan.SendPaymentRequest
import com.mifos.objects.templates.loans.LoanRepaymentTemplate
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by Rajan Maurya on 8/6/16.
 */
class LoanRepaymentPresenter @Inject constructor(private val mDataManagerLoan: DataManagerLoan, private val mDataManager: DataManager) : BasePresenter<LoanRepaymentMvpView?>() {
    val LOG_TAG = javaClass.simpleName
    private val mSubscriptions: CompositeSubscription
    override fun attachView(mvpView: LoanRepaymentMvpView?) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
        mSubscriptions.unsubscribe()
    }

    fun loanLoanRepaymentTemplate(loanId: Int) {
        checkViewAttached()
        mvpView!!.showProgressbar(true)
        mSubscriptions.add(mDataManagerLoan.getLoanRepayTemplate(loanId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<LoanRepaymentTemplate?>() {
                    override fun onCompleted() {
                        mvpView!!.showProgressbar(false)
                    }

                    override fun onError(e: Throwable) {
                        mvpView!!.showProgressbar(false)
                        mvpView!!.showError(R.string.failed_to_load_loanrepayment)
                    }

                    override fun onNext(loanRepaymentTemplate: LoanRepaymentTemplate?) {
                        mvpView!!.showProgressbar(false)
                        mvpView!!.showLoanRepayTemplate(loanRepaymentTemplate)
                    }
                }))
    }

    fun loadLoanRepaySchedule(loanId: Int) {
        checkViewAttached()
        mvpView!!.showProgressbar(true)
        mSubscriptions.add(mDataManager.getLoanRepaySchedule(loanId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<LoanWithAssociations?>() {
                override fun onCompleted() {
                    mvpView!!.showProgressbar(false)
                }

                override fun onError(e: Throwable) {
                    mvpView!!.showProgressbar(false)
                     mvpView!!.showFetchingError("Failed to load LoanRepayment")
                }

                override fun onNext(loanWithAssociations: LoanWithAssociations?) {
                    mvpView!!.showProgressbar(false)
                    loanWithAssociations?.let { mvpView!!.showLoanRepaySchedule(it) }
                }
            }))
    }

    fun submitPayment(loanId: Int, request: LoanRepaymentRequest?) {
        checkViewAttached()
        mvpView!!.showProgressbar(true)
        mSubscriptions.add(mDataManagerLoan.submitPayment(loanId, request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<LoanRepaymentResponse?>() {
                    override fun onCompleted() {
                        mvpView!!.showProgressbar(false)
                    }

                    override fun onError(e: Throwable) {
                        mvpView!!.showProgressbar(false)
                        mvpView!!.showError(R.string.payment_failed)
                    }

                    override fun onNext(loanRepaymentResponse: LoanRepaymentResponse?) {
                        mvpView!!.showProgressbar(false)
                        mvpView!!.showPaymentSubmittedSuccessfully(loanRepaymentResponse)
                    }
                }))
    }

    fun sendPaymentLink(request: SendPaymentRequest?) {
        checkViewAttached()
        mvpView!!.showProgressbar(true)
        mSubscriptions.add(mDataManagerLoan.sendPaymentLink(request)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Subscriber<String?>() {
                override fun onCompleted() {
                    mvpView!!.showProgressbar(false)
                }

                override fun onError(e: Throwable) {
                    mvpView!!.showProgressbar(false)
                    mvpView!!.showError(R.string.payment_failed)
                }

                override fun onNext(responsePayment: String?) {
                    mvpView!!.showProgressbar(false)
                    mvpView!!.showPaymentSentSuccessfully(responsePayment)
                }
            }))
    }

    fun checkDatabaseLoanRepaymentByLoanId(loanId: Int) {
        checkViewAttached()
        mvpView!!.showProgressbar(true)
        mSubscriptions.add(mDataManagerLoan.getDatabaseLoanRepaymentByLoanId(loanId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : Subscriber<LoanRepaymentRequest?>() {
                    override fun onCompleted() {}
                    override fun onError(e: Throwable) {
                        mvpView!!.showProgressbar(false)
                        mvpView!!.showError(R.string.failed_to_load_loanrepayment)
                    }

                    override fun onNext(loanRepaymentRequest: LoanRepaymentRequest?) {
                        mvpView!!.showProgressbar(false)
                        if (loanRepaymentRequest != null) {
                            mvpView!!.showLoanRepaymentExistInDatabase()
                        } else {
                            mvpView!!.showLoanRepaymentDoesNotExistInDatabase()
                        }
                    }
                })
        )
    }

    init {
        mSubscriptions = CompositeSubscription()
    }
}