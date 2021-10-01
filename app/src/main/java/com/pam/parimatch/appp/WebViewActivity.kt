package com.pam.parimatch.appp

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pam.parimatch.appp.databinding.ActivityWebViewBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding2ike33: ActivityWebViewBinding
    private var filepathBack2ike33: ValueCallback<Array<Uri>>? = null
    private var uriView2ike33 = Uri.EMPTY
    private lateinit var viewmodel2ike33: ViewModel2ike33

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2ike33 = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding2ike33.root)

        viewmodel2ike33 = ViewModelProvider(this)[ViewModel2ike33::class.java]
        viewmodel2ike33.isNetworkIsAvailableWebView2ike33(this)
        viewmodel2ike33.isInternetPresent2ike33.observe(this) {
            if (!it) {
                binding2ike33.swl2ike33.animate().alpha(0f)
                binding2ike33.fab2ike33.animate().alpha(1f)
                binding2ike33.tvLostInternetConnection2ike33.animate().alpha(1f)
                binding2ike33.wv2ike33.animate().alpha(0f)
                binding2ike33.fab2ike33.setOnClickListener {
                    viewmodel2ike33.checkManuallyIsNetworkAvailable2ike33(this)
                }
            } else {
                if (binding2ike33.swl2ike33.alpha != 1f) {
                    binding2ike33.swl2ike33.animate().alpha(1f)
                    binding2ike33.fab2ike33.animate().alpha(0f)
                    binding2ike33.tvLostInternetConnection2ike33.animate().alpha(0f)
                    binding2ike33.wv2ike33.animate().alpha(1f)
                    binding2ike33.wv2ike33.loadUrl(binding2ike33.wv2ike33.url ?: return@observe)
                }
            }
        }

        binding2ike33.swl2ike33.setOnRefreshListener{
            binding2ike33.wv2ike33.loadUrl(binding2ike33.wv2ike33.url ?: return@setOnRefreshListener)
            binding2ike33.swl2ike33.isRefreshing = false
        }


        binding2ike33.wv2ike33.run {
            settings.run {
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                useWideViewPort = true
                loadWithOverviewMode = true
                defaultTextEncodingName = "utf-8"
                builtInZoomControls = true
                domStorageEnabled = true
                displayZoomControls = false
                loadsImagesAutomatically = true
                mediaPlaybackRequiresUserGesture = false
                javaScriptEnabled = true
            }
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view2ike33: WebView?,
                    request2ike33: WebResourceRequest?
                ): Boolean {
                    when {
                        listOf("mailto:", "tel:").any {
                            request2ike33?.url.toString().startsWith(it)
                        } -> {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    request2ike33?.url
                                )
                            )
                            return true
                        }
                        listOf("facebook", "twitter", "vk.com", "youtube").any {
                            request2ike33?.url.toString().contains(it)
                        } -> {
                            return true
                        }
                        else -> return false
                    }
                }

                override fun onPageFinished(view2ike33: WebView?, url2ike33: String?) {
                    getSharedPreferences("SP_2ike33", MODE_PRIVATE).edit()
                        .putString("Last_Page_2ike33", url2ike33).apply()
                    super.onPageFinished(view2ike33, url2ike33)
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView2ike33: WebView?,
                    filePathCallback2ike33: ValueCallback<Array<Uri>>?,
                    fileChooserParams2ike33: FileChooserParams?
                ): Boolean {
                    checkPermissions()
                    filepathBack2ike33 = filePathCallback2ike33
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE).let { captureIntent2ike33 ->
                        captureIntent2ike33.resolveActivity(packageManager)?.let {
                            val date2ike33 =
                                SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(
                                    Date()
                                )
                            val fileDirectory2ike33 = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                            File.createTempFile("TMP${date2ike33}_2ike33", ".jpg", fileDirectory2ike33)
                                .let {
                                    uriView2ike33 = FileProvider.getUriForFile(
                                        this@WebViewActivity, "${packageName}.provider",
                                        it
                                    )
                                }
                            val actionIntent2ike33 = Intent(Intent.ACTION_GET_CONTENT).apply {
                                addCategory(Intent.CATEGORY_OPENABLE)
                                type = "image/*"
                            }
                            captureIntent2ike33.putExtra(MediaStore.EXTRA_OUTPUT, uriView2ike33)
                            captureIntent2ike33.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            Intent(Intent.ACTION_CHOOSER).let { intentChooser2ike33 ->
                                intentChooser2ike33.putExtra(Intent.EXTRA_INTENT, captureIntent2ike33)
                                intentChooser2ike33.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent2ike33))
                                startActivityForResult(intentChooser2ike33, 0)
                                return true
                            }
                        }
                    }
                    return super.onShowFileChooser(webView2ike33, filePathCallback2ike33, fileChooserParams2ike33)
                }
            }


            getSharedPreferences("SP_2ike33", MODE_PRIVATE).getString("Last_Page_2ike33", null)
                .let { sp2ike33 ->
                    if (sp2ike33 != null) {
                        loadUrl(sp2ike33)
                    } else {
                        loadUrl(intent.getStringExtra("webURL2ike33") ?: return@run)

                    }
                }
        }
    }

    private fun checkPermissions() {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p02ike33: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    pr2ike33: MutableList<PermissionRequest>?,
                    pt2ike33: PermissionToken?
                ) {
                }
            }).check()
    }

    override fun onBackPressed() {
        if (binding2ike33.wv2ike33.canGoBack() && binding2ike33.wv2ike33.isFocused) {
            binding2ike33.wv2ike33.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode2ike33: Int, resultCode2ike33: Int, data2ike33: Intent?) {
        if (requestCode2ike33 == 0) {
            filepathBack2ike33?.let { filepathBack2ike33 ->
                val uriResult2ike33 = if (data2ike33 == null || resultCode2ike33 != RESULT_OK ) null else data2ike33.data
                if (uriResult2ike33 != null) {
                    filepathBack2ike33.onReceiveValue(arrayOf(uriResult2ike33))
                } else {
                    filepathBack2ike33.onReceiveValue(arrayOf(uriView2ike33))
                }
            }
            filepathBack2ike33 = null
        }
        super.onActivityResult(requestCode2ike33, resultCode2ike33, data2ike33)
    }
}