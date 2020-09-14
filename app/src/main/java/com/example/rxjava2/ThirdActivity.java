package com.example.rxjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        testObservable();
    }

    private void testObservable(){

        int[] arr = {1,2,3,4,5};

       Observable.range(1,10)
//               .skip(3)
//               .take(3)
//               .skipLast(3)
               .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> Log.d(MainActivity.TAG, "testObservable: " + i));
    }
}