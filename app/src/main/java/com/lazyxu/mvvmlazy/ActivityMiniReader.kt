package com.lazyxu.mvvmlazy

import android.graphics.PixelFormat
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mini_reader.*


class ActivityMiniReader : AppCompatActivity() {
//class ActivityMiniReader : Activity() {

    var mUrl: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_reader)
        initWebView()
        initActions()
        mUrl = "https://s.click.taobao.com/zus6Xvu?jumpy=2&jumpyket6=2"
        val urltest = reportUrl(mUrl)
        val urltest3 = reportUrl("https://s.click.taobao.com/zus6Xvu?jumpy=2&jumpyket6=2&jumpytest=2")

        val urltest2 = reportUrl("https://s.click.taobao.com/zus6Xvu?jumpyket6=2&jumpy=2")
        val urltest4 = reportUrl("https://s.click.taobao.com/zus6Xvu?jumpyket6=2")

        webview.loadUrl(urltest)
    }


    private fun initWebView() {


        val webSettings = webview.settings

        webSettings.allowFileAccess = true
        webSettings.setAppCacheEnabled(true)
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 关闭 webview 中缓存
        webSettings.allowFileAccess = true // 设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true // 支持通过 JS 打开新窗口
        webSettings.loadsImagesAutomatically = true // 支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8"// 设置编码格式
        //设置支持JS
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        ////////////////////////////////////////////

        //设置是否使用预览模式加载页面
        webSettings.loadWithOverviewMode = true
        //设置是否很使用viewport
        // false：加载页面的宽度总是使用webview
        //true：由页面的viewport标签决定
        webSettings.useWideViewPort = true
        webSettings.allowContentAccess = true
        //设置是否支持文件访问
        webSettings.allowFileAccess = true
        webSettings.allowFileAccessFromFileURLs = true

        // 设置可以支持缩放
        webSettings.setSupportZoom(true)
        // 设置出现缩放工具
        webSettings.builtInZoomControls = false
        webSettings.defaultFontSize = 20
        window.setFormat(PixelFormat.TRANSLUCENT)
        //
        webSettings.userAgentString = webSettings.userAgentString + " " + R.string.app_name
        /** 加载前清除缓存  */
        //允许HTTPS和HTTP混合，否则HTTPS页面里面嵌入HTTP的Image不能展示
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;
        }
        //是否加载阻塞网络图片
        //webSettings.blockNetworkImage = false

        //设置是否使用预览模式加载页面
        webSettings.loadWithOverviewMode = true
        //设置是否很使用viewport
        // false：加载页面的宽度总是使用webview
        //true：由页面的viewport标签决定
        webSettings.useWideViewPort = true
        webSettings.allowContentAccess = true
        //设置是否支持文件访问
        webSettings.allowFileAccess = true
        webSettings.allowFileAccessFromFileURLs = true


        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        webview.clearCache(true)

    }

    private fun initActions() {


        webview.webViewClient = object : WebViewClient() {
            override fun onReceivedHttpError(view: WebView, request: WebResourceRequest?, errorResponse: WebResourceResponse) {
                super.onReceivedHttpError(view, request, errorResponse)
                // 这个方法在 android 6.0才出现
                val statusCode = errorResponse.statusCode
//                if (404 == statusCode || 500 == statusCode) {}
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                //6.0以下执行
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return
                }
            }


            private fun checkIntercept(url: String): Boolean {
                if (url.startsWith("http://", true)
                        || url.startsWith("https://", true)
                        || url.startsWith("ftp://", true)
                        || url.startsWith("rtsp://", true)) {
                    return false
                }

                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

//                webview_title.text = if (webview.title.isNullOrEmpty()) {
//                    ""
//                } else {
//                    webview.title
//                }
            }

            override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
            ) {
                //super.onReceivedSslError(view, handler, error)
                // 不要使用super，否则有些手机访问不了，因为包含了一条 handler.cancel()
                // super.onReceivedSslError(view, handler, error);
                // 接受所有网站的证书，忽略SSL错误，执行访问网页
                handler?.proceed()
            }


            private fun __shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                try {

                    if (checkIntercept(url)) {
                        //val intent : Intent = Intent(Intent.ACTION_VIEW, uri)
                        //startActivity(intent)
//                        APKHelper.startAppByUrl(url)
                        return true
                    }
                } catch (e: Exception) {
                    return false
                }
                //view?.loadUrl(url)
                //return true
                return false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                //return super.shouldOverrideUrlLoading(view, url)
                Log.e("wvjbtest", "shouldOverrideUrlLoading-url:$url")
                return if (__shouldOverrideUrlLoading(view, url ?: "")) {
                    true
                } else {
                    super.shouldOverrideUrlLoading(view, url)
                }
            }

            override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
            ): Boolean {
                //return super.shouldOverrideUrlLoading(view, request)
                val uri = request?.url
                val url = uri?.toString()
                Log.e("wvjbtest", "WebResourceRequest-url:$url")
                return if (__shouldOverrideUrlLoading(view, url ?: "")) {
                    true
                } else {
                    super.shouldOverrideUrlLoading(view, url)
                }
            }

        }
        webview.webChromeClient = object : WebChromeClient() {

            // 设置网页加载的进度条
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            // 设置程序的Title
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
            }
        }
    }

    private fun reportUrl(loadUrl: String): String {
        val str = "&jumpyket6=2|\\?jumpyket6=2"
        return loadUrl.replace(str.toRegex(), "")
    }
}
