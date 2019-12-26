package org.wit.hillforts.views.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wit.hillforts.R
import org.wit.hillforts.views.login.LoginView

class SplashScreenView : AppCompatActivity() {
  override fun onCreate(savedInstaceState: Bundle?) {
    super.onCreate(savedInstaceState)
    setContentView(R.layout.activity_splashscreen)

    val background = object : Thread() {
      override fun run() {
        try {
          Thread.sleep(5000)
          startActivity(Intent(baseContext, LoginView::class.java))
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }
    background.start()
  }
}