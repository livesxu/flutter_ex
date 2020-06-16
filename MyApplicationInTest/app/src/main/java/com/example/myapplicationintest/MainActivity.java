package com.example.myapplicationintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myapplicationintest.databinding.ActivityMainBinding;

import io.flutter.embedding.android.FlutterActivity;//注意这儿的FlutterActivity所属的库！！！
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.view.FlutterMain;

//https://flutter.dev/docs/development/add-to-app/android/add-flutter-screen
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mainBinding.getRoot());

    }

    public void clickAction(View view) {
        //进入到默认flutter - main 页面
//        startActivity(FlutterActivity.createDefaultIntent(this));

        //进入到flutter - 指定route页面
//        startActivity(FlutterActivity.withNewEngine().initialRoute("123").build(this));

        //进入到指定方法页面 - example：test   ! MyApplication 中对此进行了注册！
        startActivity(FlutterActivity.withCachedEngine("test").build(this));
    }
}
