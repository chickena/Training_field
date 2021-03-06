package com.tatsuya.main.schedulev2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson

class MemoActivity : AppCompatActivity() {
    var temp: String = ""
//    var EXTRA_ITEM_ID = "extra_item_id"

//    fun createIntent(context: Context, itemid: Int): Intent {
//        val intent = Intent(context, MemoActivity::class.java)
//        intent.putExtra(EXTRA_ITEM_ID, itemid)
//        return intent
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        val intent: Intent = intent
        this.temp = intent.extras.get("day").toString()

        val transaction = supportFragmentManager.beginTransaction()
        val memoFragment = MemoFragment()
        val bundle = Bundle()
        bundle.putString("day", temp)
        memoFragment.arguments = bundle
        transaction.replace(R.id.memoFragmentLayout, memoFragment)
        transaction.commit()
    }

    //    Fragment->doneボタンを押した時の処理,保存など
    fun donePress(key: String, value: String) {
        val pref = getSharedPreferences("test", Context.MODE_PRIVATE)
//        Jsonの場合
//        val memo = Memo()
//        memo.value = value
//        pref.applytoJson(key,memo)
//        val fromj = pref.getFromJson(key,memo::class.java)
        val editor: SharedPreferences.Editor = pref.edit()
        editor
                .putString(key, value)
                .apply()
        finish()
    }

    //    Fragment->何も入力されていない時にエラー文を出す
//    fun errorToast() {
//        Toast.makeText(this, "入力されていません", Toast.LENGTH_LONG).show()
//    }

    //    Fragment->DatePickerを呼び出す関数
//    fun setDate() {
//        val datepicker: DatePick = DatePick()
//        datepicker.show(supportFragmentManager, "datePicker")
//    }

    //    DatePickerで日付を選択しOKを押した時の処理?
    //    のはずだがなぜか呼び出されない
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        temp = "$year/" + (month + 1) + "/$dayOfMonth"
//        val transaction = supportFragmentManager.beginTransaction()
//        val memoFragment = MemoFragment()
//        val bundle = Bundle()
//        bundle.putString("day", temp)
//        memoFragment.arguments = bundle
//        transaction.commit()
//        memoFragment.dateTextFragSave(temp)
//    }

    //    Json形式に変換してファイルに保存する拡張関数
    fun SharedPreferences.applytoJson(key: String, value: Any) {
        edit().putString(key, Gson().toJson(value)).apply()
    }

    //    ファイルのJson形式の値を変換する拡張関数
    fun <T : Any> SharedPreferences.getFromJson(key: String, clazz: Class<T>): T {
        return Gson().fromJson(getString(key, ""), clazz)
    }
}

//class Memo{
//    lateinit var value: String
//}
