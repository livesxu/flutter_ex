package com.example.myapplicationintest;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import io.flutter.app.FlutterApplication;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.view.FlutterMain;

public class MyApplication extends FlutterApplication {

    public FlutterEngine engine;

    public MethodChannel methodChannel;

    public EventChannel eventChannel;

    public EventChannel.EventSink eventSink;

    @Override
    public void onCreate() {
        super.onCreate();

        engine = new FlutterEngine(this);

        DartExecutor.DartEntrypoint dartEntrypoint = new DartExecutor.DartEntrypoint(FlutterMain.findAppBundlePath(),"test");
        //engine启动test
        engine.getDartExecutor().executeDartEntrypoint(dartEntrypoint);

        FlutterEngineCache.getInstance().put("test",engine);

        methodChannel = new MethodChannel(engine.getDartExecutor(),"channel_1");

        methodChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                if (call.method.equals("method_1")) {

                    String string = call.argument("count");

                    Toast.makeText(getApplicationContext(),string,Toast.LENGTH_SHORT).show();

                    eventSink.success("count " + string);
                }
            }
        });

        eventChannel = new EventChannel(engine.getDartExecutor(),"event_1");

        eventChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {

                eventSink = events;
            }

            @Override
            public void onCancel(Object arguments) {


            }
        });
    }
}
