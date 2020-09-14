package com.example.rxjava2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class SecondActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        disposable.add(  getCarsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getCarsObserver()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    private DisposableObserver<Car> getCarsObserver(){
        return  new DisposableObserver<Car>() {

            @Override
            public void onNext(Car car) {
                Log.d(MainActivity.TAG, "Next: " + car.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(MainActivity.TAG, "onError: " + e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Observable<Car> getCarsObservable(){
        List<Car> cars = new ArrayList<>();
        cars.add(new Car ("Mercedes", 400));
        cars.add(new Car ("BMW", 300));
        cars.add(new Car ("Nissan", 200));
        cars.add(new Car ("Lada", 100));

       // return Observable.fromIterable(cars);

        return  Observable.create(emitter -> {
            for (Car car: cars){
                if(!emitter.isDisposed() && car.getHP() > 300)
                emitter.onNext(car);
            }
            if(!emitter.isDisposed())
            emitter.onComplete();
        });

    }
}