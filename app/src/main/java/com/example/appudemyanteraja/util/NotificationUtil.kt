package com.example.appudemyanteraja.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.appudemyanteraja.R
import com.example.appudemyanteraja.view.MainActivity

class NotificationUtil(val context: Context) {

    private val CHANNEL_ID = "Dogs channel id"
    private val NOTIFICATION_ID = 220798

    /**
     * Method Generate atau Insialisasi UI Notification
     */

    fun createNotification () {

        createNotificationChannel() // Config Notification

        val intent = Intent(context, MainActivity::class.java).apply { // Ketika notif diklik akan membuka main activity dan membuat task baru atau menghapus task sebelumnya
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationIcon = BitmapFactory.decodeResource(context.resources, R.drawable.icon_app)

        val generateNotification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo_al)
            .setLargeIcon(notificationIcon)
            .setContentTitle("Aplikasi Anjing")
            .setContentText("Ini isi dari notifikasi aplikasi anjing")
            .setStyle(  // Set Gambar icon notif ukuran gede
                NotificationCompat.BigPictureStyle()
                    .bigPicture(notificationIcon)
                    .bigLargeIcon(null)
            )
            .setContentIntent(pendingIntent) // saat aplikasi berjalan dan ketika notif itu di klik akan menjalankan activity pertama
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Menimbulkan Notifikasi di status bar hape android
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, generateNotification)
    }

    /**
     * Method Config Notification
     */

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionNotif = "Aplikasi Anjing"
            val importance = NotificationManager.IMPORTANCE_DEFAULT     // notif muncul di mana-mana, berisik kalo ada notifsound, tetapi tidak mengganggu secara visual.
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionNotif
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}