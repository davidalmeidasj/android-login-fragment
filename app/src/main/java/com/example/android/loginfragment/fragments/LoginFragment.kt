package com.example.android.loginfragment.fragments

import android.app.Fragment
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TextInputLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.android.loginfragment.R.layout.fragment_login
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_login.*
import rx.subscriptions.CompositeSubscription

class LoginFragment: Fragment() {


    companion object {
        @JvmField val TAG = LoginFragment.javaClass.simpleName
    }

    private var RC_SIGN_IN = 9001

    private var mEtEmail: EditText? = null
    private var mEtPassword: EditText? = null
    private var mBtLogin: Button? = null
    private var mTvRegister: TextView? = null
    private var mTvForgotPassword: TextView? = null
    private var mTiEmail: TextInputLayout? = null
    private var mTiPassword: TextInputLayout? = null
    private var mProgressBar: ProgressBar? = null


    private var mSubscriptions: CompositeSubscription? = null
    private var mSharedPreferences: SharedPreferences? = null



    private var mGoogleSignInButton: SignInButton? = null
    private var mFacebookSignInButton: LoginButton? = null

    private var mFacebookCallbackManager: CallbackManager? = null
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mGoogleSignInClient = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View? = inflater?.inflate(fragment_login, container, false)

        view?.let {

            //FacebookSdk.sdkInitialize(view.context)
            mFacebookCallbackManager = CallbackManager.Factory.create()

            mFacebookSignInButton = facebook_sign_in_button
            mFacebookSignInButton?.setReadPermissions("email")
            mFacebookSignInButton?.setFragment(this)

            mFacebookSignInButton?.registerCallback(mFacebookCallbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {

                        }

                        override fun onCancel() {

                        }

                        override fun onError(error: FacebookException) {
                            Log.d(LoginFragment::class.java.canonicalName, error.message)
                        }
                    }
            )

            mGoogleSignInButton = google_sign_in_button
            mGoogleSignInButton?.let {
                it.setSize(SignInButton.SIZE_STANDARD)
                it.setOnClickListener({})
            }
        }

        mSubscriptions = CompositeSubscription()

        initViews(view)
        initSharedPreferences()

        return view
    }

    private fun initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
    }

    private fun initViews(view: View?) {
        view.let {
            mEtEmail = et_email
            mEtPassword = et_senha
            mBtLogin = btn_login
            mTvRegister = tv_registrar
            mTvForgotPassword = tv_esqueci_senha
            mTiEmail = ti_email
            mTiPassword = ti_senha
            mProgressBar = progress

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSubscriptions?.unsubscribe()
    }
}