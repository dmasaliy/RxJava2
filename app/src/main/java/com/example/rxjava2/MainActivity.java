package com.example.rxjava2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

public static final String TAG = "Rx";
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> carsObservable = getCarsObservable();

        DisposableObserver<String> carsObserver = getCarsObserver();
        DisposableObserver<String> carsLongNameObserver = getCarsLongNameObserver();

        compositeDisposable.add(carsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(carsObserver));

        compositeDisposable.add(carsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(s ->s.length()>3)
                .subscribeWith(carsLongNameObserver));

    }

    private DisposableObserver<String> getCarsObserver(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private Observable<String> getCarsObservable(){
        return Observable.fromArray("BMW", "Mercedes", "Lada", "Nissan", "RR");
    }

    private DisposableObserver<String> getCarsLongNameObserver(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}