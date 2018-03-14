package com.example.android.loginfragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.android.loginfragment.fragments.LoginFragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.concurrent.Callable


class MainActivity : AppCompatActivity() {

    private var mLoginFragment : LoginFragment? = null

    companion object {
        @JvmField val TAG = MainActivity.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            loadFragment()
    }

    private fun loadFragment() {

        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment()
        }

        fragmentManager.beginTransaction().replace(R.id.fragmentFrame, mLoginFragment, LoginFragment.TAG).commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val data = intent?.data?.lastPathSegment

        data.let {
            Log.d(TAG, "onNewIntent $data")
        }
    }

    private fun signInWithGoogle() {

    }
}