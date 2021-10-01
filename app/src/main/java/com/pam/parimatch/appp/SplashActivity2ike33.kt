package com.pam.parimatch.appp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pam.parimatch.appp.databinding.ActivityMain2ike33Binding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity2ike33 : AppCompatActivity() {

    private lateinit var viewModel2ike332Ike33: ViewModel2ike33
    private lateinit var bind2ike33: ActivityMain2ike33Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind2ike33 = ActivityMain2ike33Binding.inflate(layoutInflater)
        setContentView(bind2ike33.root)
        getSharedPreferences("SP_2ike33", MODE_PRIVATE).getString("Last_Page_2ike33", null)?.run {
            startActivity(Intent(this@SplashActivity2ike33, WebViewActivity::class.java))
            finish()
        }
        viewModel2ike332Ike33 = ViewModelProvider(this)[ViewModel2ike33::class.java]
        viewModel2ike332Ike33.isNetworkAvailableSplash2ike33(this)
        viewModel2ike332Ike33.isInternetPresent2ike33.observe(this) { internet ->
            if (internet) {
                viewModel2ike332Ike33.isInternetPresent2ike33.removeObservers(this)
                viewModel2ike332Ike33.setupFirebase2ike33()
                viewModel2ike332Ike33.firebaseConfigLiveData2ike33.observe(this) { firebase ->
                    if (firebase) {
                        buttonShowUp2ike33()
                        bind2ike33.tvEnter2ike33.setOnClickListener {
                            bind2ike33.tvEnter2ike33.isClickable = false
                            animaSetup2ike33()
                            lifecycleScope.launch {
                                delay(4000)
                            viewModel2ike332Ike33.getUrl2ike33(this@SplashActivity2ike33.packageName)
                            viewModel2ike332Ike33.urlLiveData2ike33.observe(this@SplashActivity2ike33) { url ->
                                url?.run {
                                        startActivity(
                                            Intent(
                                                this@SplashActivity2ike33,
                                                WebViewActivity::class.java
                                            ).putExtra("webURL2ike33", url)
                                        )
                                        finish()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun animaSetup2ike33() {
        bind2ike33.ivLeftstr2ike33.animate().translationX(2000f).translationY(2000f).run {
            duration = 1000
        }
        bind2ike33.ivRightstr12ike33.animate().translationX(-2000f).translationY(2000f).run {
            duration = 1000
        }
        bind2ike33.ivRightstr22ike33.animate().translationX(2000f).translationY(-2000f).run {
            duration = 1000
        }
        bind2ike33.tvEnter2ike33.animate().alpha(0f).run {
            duration = 500
        }
        bind2ike33.pbLoading2ike33.animate().alpha(1f).run {
            startDelay = 1500
        }
    }

    private fun buttonShowUp2ike33() {
        bind2ike33.pb2ike33.animate().alpha(0f)
        bind2ike33.tvEnter2ike33.animate().alpha(1f)
    }
}