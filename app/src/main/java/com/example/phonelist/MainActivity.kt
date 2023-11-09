package com.example.phonelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phonelist.database.AppDatabase
import com.example.phonelist.database.daos.UserDao
import com.example.phonelist.databinding.ActivityMainBinding
import com.example.phonelist.databinding.ActivityNewUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.database = AppDatabase.getInstance(this)
        this.userDao = this.database.userDao()
    }

    override fun onStart() {
        super.onStart()

        loadTotalUsers()

        this.binding.btnNewUser.setOnClickListener {
            openNewUserActivity()
        }
    }

    private fun openNewUserActivity() {
        startActivity(Intent(this, NewUserActivity::class.java))
    }

    private fun loadTotalUsers() {
        this.binding.tvTotalUsers.text = "carregando..."



        CoroutineScope(Dispatchers.IO).launch {

            val total = userDao.getTotalItems()

            withContext(Dispatchers.Main){
                binding.tvTotalUsers.text = "Total de usuários: $total"}
        }
    }
}