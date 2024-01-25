import android.content.Context
import android.content.Intent
import android.widget.Toast

class appHelper {
    companion object {
        fun toastmsg(context: Context, message: String) {
            val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast.show()
        }
        fun IntentOlustur(context: Context, targetActivity: Class<*>) {
            val intent = Intent(context, targetActivity)
            context.startActivity(intent)
        }
    }
}