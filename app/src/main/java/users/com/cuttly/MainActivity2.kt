package users.com.cuttly

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import users.com.cuttly.Interface.Retroclient
import users.com.cuttly.Response.Url
import users.com.cuttly.Response.UrlResponse

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btn = findViewById<CircularProgressButton>(R.id.btn_id)
        btn.setOnClickListener {
            if (et_url.text.toString().isEmpty()) {
                Toasty.normal(applicationContext,"Url must be provided").show()
            }else
            {
                btn.startAnimation()
                Retroclient.getInstance().getapi().shorten("d78f7fea6ab02935fffede0ddd38232b",et_url.text.toString())
                        .enqueue(object: Callback<UrlResponse> {
                            override fun onResponse(call: Call<UrlResponse>, response: Response<UrlResponse>) {
                              btn.revertAnimation()
                                //Toasty.normal(applicationContext, response.body()?.url.toString(),Toasty.LENGTH_LONG).show()
                                val code = response.body()?.url?.status
                               if (code ==7){
                                   val new_url= response.body()!!.url.shortLink
                                   tv_url.text=new_url
                                   rl.visibility= View.VISIBLE
                                   btn_share.setOnClickListener {
                                       val shareUrl: String = tv_url.text.toString()
                                       val intent = Intent()
                                       intent.action = Intent.ACTION_SEND
                                       intent.putExtra(Intent.EXTRA_TEXT, shareUrl)
                                       intent.type = "text/plain"
                                       startActivity(Intent.createChooser(intent, "Share to :"))
                                   }
                                       btn_copy.setOnClickListener {
                                           val copyUrl:String = tv_url.text.toString()
                                          val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                                           val clip = ClipData.newPlainText("Copied text", copyUrl)
                                           clipboard.setPrimaryClip(clip)
                                           Toasty.normal(applicationContext, "Url copied",Toasty.LENGTH_SHORT).show()
                                       }

                               }
                               /* val url: Url? = null
                                if (url != null) {

                                    doWork(url)
                                }
                                else{

                                }*/
                            }

                            override fun onFailure(call: Call<UrlResponse>, t: Throwable) {
                                Toasty.normal(applicationContext,t.message.toString()).show()
                            }

                        })

            }
        }
    }

   /* fun doWork(url: Url) {
       val code = url.status
        val new_url= url.shortLink
        tv_url.text=new_url
        rl.visibility= View.VISIBLE
    }*/
}